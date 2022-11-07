package sh.hsp.labella.services.renderer

import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput

interface RendererService {
    fun render(renderingInput: RenderingInput): RenderingOutput
}


data class PrinterConfiguration(
    val dpi: Int
);