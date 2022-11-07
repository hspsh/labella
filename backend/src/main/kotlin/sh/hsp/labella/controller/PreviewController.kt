package sh.hsp.labella.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.model.TemplateType
import sh.hsp.labella.services.printing.PrintingService
import sh.hsp.labella.services.renderer.*
import java.awt.image.BufferedImage

@RestController
@RequestMapping(path = ["/templates/{templateId}/preview"])
class PreviewController(
    val rendererService: RendererService,
    val templateRepository: TemplateRepository
) {
    @GetMapping(produces = ["image/png"])
    fun printSvg(@PathVariable templateId: Long, @RequestBody printDTO: PreviewDTO): BufferedImage {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render = when (template.type) {
            TemplateType.SVG -> rendererService.render(
                RenderingInput.SVGRenderingInput(
                    template.template,
                    PrintDimensions.ORANGE_LABEL
                )
            )
            TemplateType.MD -> rendererService.render(
                RenderingInput.MdRenderingInput(
                    template.template,
                    PrintDimensions.ORANGE_LABEL
                )
            )
        }

        return render.image
    }
}

data class PreviewDTO(val fields: Map<String, String>) {}