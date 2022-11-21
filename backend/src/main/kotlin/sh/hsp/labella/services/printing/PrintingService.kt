package sh.hsp.labella.services.printing

import sh.hsp.labella.model.RenderingOutput

interface PrintingService {
    fun print(templateId: Long, fields: Map<String, String>)
}