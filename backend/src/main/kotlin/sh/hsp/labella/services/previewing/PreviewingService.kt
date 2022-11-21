package sh.hsp.labella.services.previewing

import sh.hsp.labella.model.RenderingOutput

interface PreviewingService {
    fun preview(templateId: Long, fields: Map<String, String>): RenderingOutput
}