package sh.hsp.labella.services.printing

import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.HandleAfterSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.util.ConcurrentLruCache
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.model.Template

@RepositoryEventHandler
class CachedLanguagePrintingService(val printingService: PrintingService) : PrintingService {
    val cache: ConcurrentLruCache<Long, RenderingOutput> =
        ConcurrentLruCache(100) { id ->
            printingService.preview(id, emptyMap())
        }

    override fun print(templateId: Long, fields: Map<String, String>) {
        printingService.print(templateId, fields)
    }

    override fun preview(templateId: Long, fields: Map<String, String>): RenderingOutput {
        if (fields.isEmpty()) { // we don't want to cache every preview, lol
            return cache.get(templateId)
        }
        return printingService.preview(templateId, fields)
    }

    @HandleAfterCreate
    fun create(template: Template) {
        evoke(template)
    }

    @HandleAfterSave
    fun evoke(template: Template) {
        template.id?.let { cache.remove(it) }
    }

}