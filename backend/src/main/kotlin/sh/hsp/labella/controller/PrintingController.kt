package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.printing.PrintingService

@RestController
@RequestMapping(path = ["/templates/{templateId}/print"])
class PrintingController(
    val printingService: PrintingService,
    val templateRepository: TemplateRepository
) {
    @PostMapping(path = ["/svg"])
    fun printSvg(@PathVariable templateId: Long) {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        printingService.printSvg(template.template, emptyMap());
    }

    @PostMapping(path = ["/md"])
    fun printMd(@PathVariable templateId: Long) {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        printingService.printMd(template.template, emptyMap());
    }
}