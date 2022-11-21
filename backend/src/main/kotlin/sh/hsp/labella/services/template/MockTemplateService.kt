package sh.hsp.labella.services.template

import sh.hsp.labella.model.TemplateService

class MockTemplateService : TemplateService {
    override fun render(templateContents: String, fields: Map<String, String>): String {
        return templateContents
    }

    override fun listFields(templateContents: String): List<String> {
        return listOf("mock")
    }
}