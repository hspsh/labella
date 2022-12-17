package sh.hsp.labella.services.previewing

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.*
import sh.hsp.labella.services.templating.TemplatingService

class LanguagePreviewingService(
    val templateService: TemplatingService,
    val svgSizeExtractor: SvgSizeExtractor,
    val SVGRendererService: MultipleSVGRenderingService,
    val labelSizeProvider: LabelSizeProvider
) : PreviewingService {

    override fun preview(templateId: Long, fields: Map<String, String>): List<RenderingOutput> {
        val render =
            templateService.template(templateId, fields).templated
                .render(
                    svgSizeExtractor,
                    labelSizeProvider,
                    SVGRendererService
                )

        return render
    }
}