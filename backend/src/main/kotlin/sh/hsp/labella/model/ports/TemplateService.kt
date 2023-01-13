package sh.hsp.labella.model.ports

interface TemplateService {
    fun render(templateContents: String, fields: Map<String, String>): String
    fun listFields(templateContents: String): Set<String>
}