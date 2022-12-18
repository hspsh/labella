package sh.hsp.labella.application.services.printing

interface PrintingService {
    fun print(templateId: Long, fields: Map<String, String>)
}