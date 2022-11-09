package sh.hsp.labella.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.hsp.labella.services.templating.TemplatingServiceImpl

@RestController
@RequestMapping(path = ["/api/templates/{templateId}/attributes"])
class TemplateAttributeController(
    val templatingServiceImpl: TemplatingServiceImpl
) {

    @GetMapping
    fun attributes(@PathVariable templateId: Long): AttributesDTO {
        return AttributesDTO(templatingServiceImpl.fields(templateId))
    }
}

data class AttributesDTO(val fields: List<String>) {}