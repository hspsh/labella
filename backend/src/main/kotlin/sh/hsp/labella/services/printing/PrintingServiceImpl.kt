package sh.hsp.labella.services.printing

import sh.hsp.labella.services.printer.PrinterService
import sh.hsp.labella.services.renderer.PrintDimensions
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.renderer.RenderingInput
import sh.hsp.labella.services.template.TemplateService
import java.awt.image.BufferedImage
import java.util.function.Function

class PrintingServiceImpl(
    private val templateService: TemplateService,
    private val rendererService: RendererService,
    private val printerService: PrinterService
) : PrintingService {
    override fun printMd(contents: String, fields: Map<String, String>) {
        print(contents, fields) { template ->
            rendererService.render(RenderingInput.MdRenderingInput(template, PrintDimensions(0, 0))).image
        }
    }

    override fun printSvg(contents: String, fields: Map<String, String>) {
        print(contents, fields) { template ->
            rendererService.render(RenderingInput.SVGRenderingInput(template, PrintDimensions(0, 0))).image
        }
    }

    private fun print(contents: String, fields: Map<String, String>, renderStrategy: Function<String, BufferedImage>) {
        val templated = templateService.render(contents, fields)
        val render = renderStrategy.apply(templated)
        printerService.print(render)
    }
}