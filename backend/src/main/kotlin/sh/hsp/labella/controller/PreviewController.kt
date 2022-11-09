package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.template.TemplateService
import java.awt.image.BufferedImage

@RestController
@CrossOrigin(origins = ["*"] )
@RequestMapping(path = ["/api/templates/{templateId}/preview"])
class PreviewController(
    val templateService: TemplateService,
    val rendererService: RendererService,
    val templateRepository: TemplateRepository
) {
    @GetMapping(produces = ["image/png"])
    fun preview(@PathVariable templateId: Long, @RequestBody printDTO: PreviewDTO): BufferedImage {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render =
            template
                .render(
                    printDTO.fields,
                    { content, fields -> templateService.render(content, fields) },
                    { _ -> null },
                    { input -> rendererService.render(input) }
                )

        return render.image
    }
}

data class PreviewDTO(val fields: Map<String, String>) {}