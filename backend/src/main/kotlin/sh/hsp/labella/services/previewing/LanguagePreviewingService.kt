package sh.hsp.labella.services.previewing

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.svg.SvgSizeExtractor
import sh.hsp.labella.services.template.TemplateService

class LanguagePreviewingService(
    val templateService: TemplateService,
    val svgSizeExtractor: SvgSizeExtractor,
    val rendererService: RendererService,
    val templateRepository: TemplateRepository
) : PreviewingService {

    override fun preview(templateId: Long, fields: Map<String, String>): RenderingOutput {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render =
            template
                .render(
                    fields,
                    { content, fields -> templateService.render(content, fields) },
                    { svgSizeExtractor.extract(it) },
                    { input -> rendererService.render(input) }
                )

        return render
    }
}