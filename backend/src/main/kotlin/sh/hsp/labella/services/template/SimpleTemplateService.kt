package sh.hsp.labella.services.template

class SimpleTemplateService : TemplateService {
    val variableRegex = Regex("""${'\\'}${'$'}\{([a-zA-Z]+)\}""")
    override fun render(templateContents: String, fields: Map<String, String>): String =
        variableRegex.replace(templateContents) { match ->
            fields.getOrDefault(
                match.groups[1]!!.value,
                match.groups[1]!!.value
            )
        }

    override fun listFields(templateContents: String): List<String> =
        variableRegex.findAll(templateContents).toList().map { it.groups[1]!!.value }


}