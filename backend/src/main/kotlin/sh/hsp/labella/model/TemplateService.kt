package sh.hsp.labella.model

interface TemplateService {
    fun render(templateContents: String, fields: Map<String, String>): String
    fun listFields(templateContents: String): List<String>
}