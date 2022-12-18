package sh.hsp.labella.peripherals.controllers

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.application.services.previewing.PreviewingService
import java.awt.image.BufferedImage

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping(path = ["/api/templates/{templateId}/preview"])
class PreviewController(
    val printingService: PreviewingService
) {
    @GetMapping(produces = ["image/png"])
    fun preview(@PathVariable templateId: Long, @RequestParam fields: Map<String, String>): BufferedImage {
        return printingService.preview(templateId, fields).firstOrNull()?.image ?: throw ResourceNotFoundException()
    }

    @GetMapping(produces = ["application/json", "application/bson"])
    fun previewAll(@PathVariable templateId: Long, @RequestParam fields: Map<String, String>): ImagesDTO {
        return ImagesDTO(
            printingService.preview(templateId, fields).map { it.image }
        )
    }

    data class ImagesDTO(val images: List<BufferedImage>)
}