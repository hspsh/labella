package sh.hsp.labella.application.services.previewing

import sh.hsp.labella.model.RenderedImage

interface PreviewingService {
    fun preview(templateId: Long, fields: Map<String, String>): List<RenderedImage>
}