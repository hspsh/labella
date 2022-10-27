package sh.hsp.labella.services.template

interface TemplateService {
    fun render(templateContents: String, fields: Map<String, String>): String
    fun listFields(templateContents: String): List<String>
}