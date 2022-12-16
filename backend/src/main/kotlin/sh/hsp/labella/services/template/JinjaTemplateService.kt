package sh.hsp.labella.services.template

import com.hubspot.jinjava.Jinjava
import sh.hsp.labella.model.TemplateService

// AM I RETARDED TO USE THIS SHIT?
class JinjaTemplateService(val jinjava: Jinjava = Jinjava()) : TemplateService {
    override fun render(templateContents: String, fields: Map<String, String>): String {
        synchronized(this) {
            jinjava.globalContext
                .setDynamicVariableResolver {
                    it
                }

            return jinjava.render(templateContents, fields)
        }
    }

    override fun listFields(templateContents: String): List<String> {
        synchronized(this) {
            val list = mutableListOf<String>()

            jinjava.globalContext
                .setDynamicVariableResolver { list.add(it) }

            jinjava.render(templateContents, null)

            return list
        }
    }
}