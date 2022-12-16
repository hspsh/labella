package sh.hsp.labella.services.previewing

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.*

class LanguagePreviewingService(
    val templateService: TemplateService,
    val svgSizeExtractor: SvgSizeExtractor,
    val SVGRendererService: MultipleSVGRenderingService,
    val templateRepository: TemplateRepository
) : PreviewingService {

    override fun preview(templateId: Long, fields: Map<String, String>): List<RenderingOutput> {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render =
            template
                .render(
                    fields,
                    templateService,
                    svgSizeExtractor,
                    SVGRendererService
                )

        return render
    }
}