package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
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