package sh.hsp.labella.application.services.templating

import sh.hsp.labella.model.RenderedTemplate

interface TemplatingService {
    fun template(templateId: Long, fields: Map<String, String>): TemplatedDTO
    fun fields(templateId: Long): List<String>
}

data class TemplatedDTO(val templated: RenderedTemplate, val fileName: String)