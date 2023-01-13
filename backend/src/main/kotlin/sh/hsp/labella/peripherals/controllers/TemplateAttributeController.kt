package sh.hsp.labella.peripherals.controllers

import org.springframework.web.bind.annotation.*
import sh.hsp.labella.application.services.templating.TemplatingService

@RestController
@RequestMapping(path = ["/api/templates/{templateId}/attributes"])
@CrossOrigin(origins = ["*"])
class TemplateAttributeController(
    val templatingService: TemplatingService
) {

    @GetMapping
    fun attributes(@PathVariable templateId: Long): AttributesDTO {
        return AttributesDTO(templatingService.fields(templateId))
    }
}

data class AttributesDTO(val fields: Set<String>) {}