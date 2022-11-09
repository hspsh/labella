package sh.hsp.labella.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.printing.LanguagePrintingService

@RestController
@RequestMapping(path = ["/api/templates/{templateId}/print"])
class PrintingController(
    val languagePrintingService: LanguagePrintingService
) {
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    fun printSvg(@PathVariable templateId: Long, @RequestBody printDTO: PrintDTO) {
        languagePrintingService.print(templateId, printDTO.fields)
    }
}

data class PrintDTO(val fields: Map<String, String>) {}