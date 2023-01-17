package sh.hsp.labella.peripherals

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.web.servlet.config.annotation.CorsRegistry
import sh.hsp.labella.model.Template
import javax.xml.parsers.DocumentBuilderFactory

@Configuration
class SpringDataRestConfiguration : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(
        config: RepositoryRestConfiguration, cors: CorsRegistry
    ) {
        config.exposeIdsFor(Template::class.java)
    }

    override fun configureValidatingRepositoryEventListener(validatingListener: ValidatingRepositoryEventListener?) {
        validatingListener?.addValidator("beforeCreate", TemplateValidator())
    }

    class TemplateValidator : Validator {
        override fun supports(clazz: Class<*>): Boolean =
            Template::class.java.equals(clazz)

        override fun validate(target: Any, errors: Errors) {
            val template = target as Template

            if (template.name != null && template.name!!.length < 3)
                errors.rejectValue("name", "name.too.short")

            DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .runCatching { parse(template.template.byteInputStream()) }
                .onFailure {
                    errors.rejectValue("name", "incorrect.template")
                }
        }
    }
}