package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.template.TemplateService

@RestController
@RequestMapping(path = ["/templates/{templateId}/attributes"])
class TemplateAttributeController(
    val templateService: TemplateService,
    val templateRepository: TemplateRepository
) {
    
    @GetMapping
    fun attributes(@PathVariable templateId: Long): AttributesDTO {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        return AttributesDTO(templateService.listFields(template.template))
    }
}

data class AttributesDTO(val fields: List<String>) {}