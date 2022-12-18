package sh.hsp.labella.application.services.templating

import sh.hsp.labella.model.Templated

interface TemplatingService {
    fun template(templateId: Long, fields: Map<String, String>): TemplatedDTO
    fun fields(templateId: Long): List<String>
}

data class TemplatedDTO(val templated: Templated, val fileName: String)