package sh.hsp.labella.application.adapters.svg

import sh.hsp.labella.application.adapters.svg.flavor.SVGFlavor
import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.model.ports.SVGRendererService

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