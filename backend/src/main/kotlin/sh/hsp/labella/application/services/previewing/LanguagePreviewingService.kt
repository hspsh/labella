package sh.hsp.labella.application.services.previewing

import sh.hsp.labella.application.services.templating.TemplatingService
import sh.hsp.labella.model.RenderedImage
import sh.hsp.labella.model.ports.LabelRescaler
import sh.hsp.labella.model.ports.MultipleSVGRenderingService
import sh.hsp.labella.model.ports.SvgSizeExtractor

class LanguagePreviewingService(
    private val templateService: TemplatingService,
    private val svgSizeExtractor: SvgSizeExtractor,
    private val SVGRendererService: MultipleSVGRenderingService,
    private val labelRescaler: LabelRescaler
) : PreviewingService {

    override fun preview(templateId: Long, fields: Map<String, String>): List<RenderedImage> {
        return templateService.template(templateId, fields).templated
            .renderToImage(
                svgSizeExtractor,
                labelRescaler,
                SVGRendererService
            )
    }
}