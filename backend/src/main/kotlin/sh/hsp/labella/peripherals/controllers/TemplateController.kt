package sh.hsp.labella.peripherals.controllers

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.model.Template
import javax.validation.*
import javax.validation.constraints.Size
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.reflect.KClass

@RestController
@RequestMapping(path = ["/api/new/templates"])
class TemplateController(val templateRepository: SpringTemplateRepository) {

    @PostMapping
    fun create(@Valid @RequestBody templateCreateDTO: TemplateCreateDTO) {
        templateRepository.save(
            Template.create(
                templateCreateDTO.name,
                templateCreateDTO.type,
                templateCreateDTO.contents
            )
        )
    }

    @GetMapping
    fun retrieve(@PageableDefault(size = 50) paging: Pageable, @PathVariable name: String) =
        templateRepository.findAllByNameContaining(name, paging).toList()
}

data class TemplateCreateDTO(
    @field:Size(min = 3)
    val name: String,

    val type: Template.TemplateType,

    @field:ValidXml()
    val contents: String
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
