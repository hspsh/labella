package sh.hsp.labella.model.ports

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderedImage

interface MultipleSVGRenderingService {
    fun renderAll(renderingInput: RenderingInput.SVGRenderingInput): List<RenderedImage>
}