package sh.hsp.labella.peripherals.services

import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.util.ConcurrentLruCache
import sh.hsp.labella.application.services.previewing.PreviewingService
import sh.hsp.labella.model.RenderedImage
import sh.hsp.labella.model.Template

//@RepositoryEventHandler
class CachedPreviewingService(val previewingService: PreviewingService) : PreviewingService {
    val cache: ConcurrentLruCache<Long, ConcurrentLruCache<Map<String, String>, List<RenderedImage>>> =
        ConcurrentLruCache(100) { key ->
            ConcurrentLruCache(3) { fields ->
                previewingService.preview(key, fields)
            }
        }

    override fun preview(templateId: Long, fields: Map<String, String>): List<RenderedImage> {
        return cache.get(templateId).get(fields)
    }

    //@HandleAfterCreate
    fun create(template: Template) {
        evoke(template)
    }

    //@HandleAfterSave
    fun evoke(template: Template) {
        cache.remove(template.id!!)
    }
}