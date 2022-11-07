package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.model.TemplateType
import sh.hsp.labella.services.printing.PrintingService

@RestController
@RequestMapping(path = ["/templates/{templateId}/print"])
class PrintingController(
    val printingService: PrintingService,
    val templateRepository: TemplateRepository
) {
    @PostMapping
    fun printSvg(@PathVariable templateId: Long, @RequestBody printDTO: PrintDTO) {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        when (template.type) {
            TemplateType.SVG -> printingService.printSvg(template.template, printDTO.fields)
            TemplateType.MD -> printingService.printMd(template.template, printDTO.fields)
        }
    }
}

data class PrintDTO(val fields: Map<String, String>) {}