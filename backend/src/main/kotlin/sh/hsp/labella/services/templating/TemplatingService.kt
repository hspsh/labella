package sh.hsp.labella.services.templating

interface TemplatingService {
    fun fields(templateId: Long): List<String>
}