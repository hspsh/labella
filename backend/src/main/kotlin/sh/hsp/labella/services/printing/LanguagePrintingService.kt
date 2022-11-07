package sh.hsp.labella.services.printing

import sh.hsp.labella.services.printer.PrinterService
import sh.hsp.labella.services.printing.converter.ImageToLanguage
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.renderer.RenderingInput
import sh.hsp.labella.services.template.TemplateService
import java.awt.image.BufferedImage
import java.util.function.Function

class LanguagePrintingService(
    private val templateService: TemplateService,
    private val rendererService: RendererService,
    private val imageToLanguage: ImageToLanguage,
    private val printerService: PrinterService
) : PrintingService {
    override fun printMd(contents: String, fields: Map<String, String>) {
        print(contents, fields) { template -> rendererService.render(RenderingInput.MdRenderingInput(template)).image }
    }

    override fun printSvg(contents: String, fields: Map<String, String>) {
        print(contents, fields) { template -> rendererService.render(RenderingInput.SVGRenderingInput(template)).image }
    }

    private fun print(contents: String, fields: Map<String, String>, renderStrategy: Function<String, BufferedImage>) {
        val templated = templateService.render(contents, fields)
        val render = renderStrategy.apply(templated)
        val language = imageToLanguage.convert(render)
        printerService.print(language)
    }
}