package sh.hsp.labella.model

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput

interface SVGRendererService {
    fun render(renderingInput: RenderingInput.SVGRenderingInput): RenderingOutput

    data class PrinterConfiguration(
        val dpi: Int
    );
}