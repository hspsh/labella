package sh.hsp.labella.model.ports

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderedImage

interface SVGRendererService {
    fun render(renderingInput: RenderingInput.SVGRenderingInput): RenderedImage

    data class PrinterConfiguration(
        val dpi: Int
    );
}