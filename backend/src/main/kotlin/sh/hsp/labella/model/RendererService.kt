package sh.hsp.labella.model

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput

interface RendererService {
    fun render(renderingInput: RenderingInput): RenderingOutput

    data class PrinterConfiguration(
        val dpi: Int
    );
}