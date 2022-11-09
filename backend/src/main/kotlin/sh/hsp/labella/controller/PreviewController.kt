package sh.hsp.labella.controller

import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.printing.LanguagePrintingService
import java.awt.image.BufferedImage

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping(path = ["/api/templates/{templateId}/preview"])
class PreviewController(
    val languagePrintingService: LanguagePrintingService
) {
    @GetMapping(produces = ["image/png"])
    fun preview(@PathVariable templateId: Long, @RequestParam fields: Map<String, String>): BufferedImage {
        return languagePrintingService.preview(templateId, fields).image
    }
}