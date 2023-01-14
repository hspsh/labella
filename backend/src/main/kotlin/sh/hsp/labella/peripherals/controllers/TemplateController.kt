package sh.hsp.labella.peripherals.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.hsp.labella.model.Template
import sh.hsp.labella.peripherals.adapters.SpringTemplateRepository
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import javax.validation.constraints.Size
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.reflect.KClass

@RestController
@RequestMapping(path = ["/api/new/templates"])
class TemplateController(val templateRepository: SpringTemplateRepository) {

//    @PostMapping
//    fun create(@RequestBody templateCreateDTO: TemplateCreateDTO) {
//        templateRepository.save(
//            Template.create(tem)
//        )
//    }

}

data class TemplateCreateDTO(
    @Size(min = 3)
    val name: String,

    val type: Template.TemplateType,

    @ValidXml()
    val template: Template
);

class SvgValidator : ConstraintValidator<ValidXml, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        DocumentBuilderFactory.newInstance().newDocumentBuilder()
            .runCatching { parse(value!!.byteInputStream()) }
            .onFailure {
                return false
            }
        return true
    }
}

@Constraint(validatedBy = [SvgValidator::class])
annotation class ValidXml(
    val message: String = "{incorrect.xml}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<in Payload>> = []
)
