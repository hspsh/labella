package sh.hsp.labella.application.services.previewing

import sh.hsp.labella.model.RenderingOutput

interface PreviewingService {
    fun preview(templateId: Long, fields: Map<String, String>): List<RenderingOutput>
}