package sh.hsp.labella.controller

import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.previewing.PreviewingService
import sh.hsp.labella.services.printing.PrintingService
import java.awt.image.BufferedImage

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping(path = ["/api/templates/{templateId}/preview"])
class PreviewController(
    val printingService: PreviewingService
) {
    @GetMapping(produces = ["image/png"])
    fun preview(@PathVariable templateId: Long, @RequestParam fields: Map<String, String>): BufferedImage {
        return printingService.preview(templateId, fields).image
    }
}