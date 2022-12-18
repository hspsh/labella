package sh.hsp.labella.application.services.previewing

import sh.hsp.labella.application.services.templating.TemplatingService
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.model.ports.LabelSizeProvider
import sh.hsp.labella.model.ports.MultipleSVGRenderingService
import sh.hsp.labella.model.ports.SvgSizeExtractor

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