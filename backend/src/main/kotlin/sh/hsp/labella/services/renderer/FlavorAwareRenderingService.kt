package sh.hsp.labella.services.renderer

import sh.hsp.labella.model.RendererService
import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.services.svg.flavor.SVGFlavor

class FlavorAwareRenderingService(val rendererService: RendererService, val flavors: List<SVGFlavor>) :
    RendererService {
    override fun render(renderingInput: RenderingInput): RenderingOutput {
        if (renderingInput is RenderingInput.SVGRenderingInput) {
            return flavors.foldRight(renderingInput) { flavor, input ->
                RenderingInput.SVGRenderingInput(
                    flavor.flavor(input.content),
                    input.printDimensions
                )
            }.let { rendererService.render(it) }
        }

        return rendererService.render(renderingInput)
    }
}