package sh.hsp.labella.services.printing

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.services.previewing.PreviewingService
import sh.hsp.labella.services.printer.LanguagePrinterService
import sh.hsp.labella.services.printer.converter.ImageToLanguage
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.svg.SvgSizeExtractor
import sh.hsp.labella.services.template.TemplateService

class LanguagePrintingService(
    val previewingService: PreviewingService,
    val imageToLanguage: ImageToLanguage,
    val languagePrinterService: LanguagePrinterService,
) : PrintingService {
    override fun print(templateId: Long, fields: Map<String, String>) {
        previewingService.preview(templateId, fields)
            .printViaLanguage(
                { image -> imageToLanguage.convert(image) },
                { language -> languagePrinterService.print(language) }
            )
    }
}