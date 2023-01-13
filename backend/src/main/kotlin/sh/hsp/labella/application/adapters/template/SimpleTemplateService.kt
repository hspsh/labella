package sh.hsp.labella.application.adapters.template

import sh.hsp.labella.model.ports.TemplateService

class SimpleTemplateService : TemplateService {
    val variableRegex = Regex("""\{\{([a-zA-Z0-9]+)\}\}""")
    override fun render(templateContents: String, fields: Map<String, String>): String =
        variableRegex.replace(templateContents) { match ->
            fields.getOrDefault(
                match.groups[1]!!.value,
                match.groups[1]!!.value
            )
        }

    override fun listFields(templateContents: String): Set<String> =
        variableRegex.findAll(templateContents).toSet().map { it.groups[1]!!.value }.toSet()


}