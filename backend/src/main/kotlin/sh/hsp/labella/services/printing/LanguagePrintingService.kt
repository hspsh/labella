package sh.hsp.labella.services.printing

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.services.printer.LanguagePrinterService
import sh.hsp.labella.services.printer.converter.ImageToLanguage
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.template.TemplateService

class LanguagePrintingService(
    val templateService: TemplateService,
    val rendererService: RendererService,
    val imageToLanguage: ImageToLanguage,
    val languagePrinterService: LanguagePrinterService,
    val templateRepository: TemplateRepository
) : PrintingService {
    override fun print(templateId: Long, fields: Map<String, String>) {
        preview(templateId, fields)
            .printViaLanguage(
                { image -> imageToLanguage.convert(image) },
                { language -> languagePrinterService.print(language) }
            )
    }

    override fun preview(templateId: Long, fields: Map<String, String>): RenderingOutput {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render =
            template
                .render(
                    fields,
                    { content, fields -> templateService.render(content, fields) },
                    { _ -> null },
                    { input -> rendererService.render(input) }
                )

        return render
    }

}