package sh.hsp.labella.services.renderer.svg

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.model.SVGRendererService
import sh.hsp.labella.services.svg.flavor.SVGFlavor

class FlavorAwareRenderingService(val SVGRendererService: SVGRendererService, val flavors: List<SVGFlavor>) :
    SVGRendererService {
    override fun render(renderingInput: RenderingInput.SVGRenderingInput): RenderingOutput {
            return flavors.foldRight(renderingInput) { flavor, input ->
                RenderingInput.SVGRenderingInput(
                    flavor.flavor(input.content),
                    input.printDimensions
                )
            }.let { SVGRendererService.render(it) }
    }
}