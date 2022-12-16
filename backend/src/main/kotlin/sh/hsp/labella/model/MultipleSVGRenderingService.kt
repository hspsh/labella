package sh.hsp.labella.model

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput

interface MultipleSVGRenderingService {
    fun renderAll(renderingInput: RenderingInput.SVGRenderingInput): List<RenderingOutput>
}