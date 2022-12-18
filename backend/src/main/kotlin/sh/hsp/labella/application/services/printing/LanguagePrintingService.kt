package sh.hsp.labella.application.services.printing

import sh.hsp.labella.application.services.previewing.PreviewingService
import sh.hsp.labella.model.ports.ImageToLanguage
import sh.hsp.labella.model.ports.LanguagePrinterService

class LanguagePrintingService(
    val previewingService: PreviewingService,
    val imageToLanguage: ImageToLanguage,
    val languagePrinterService: LanguagePrinterService,
) : PrintingService {
    override fun print(templateId: Long, fields: Map<String, String>) {
        previewingService.preview(templateId, fields)
            .forEach {
                it.printViaLanguage(
                    imageToLanguage,
                    languagePrinterService
                )
            }

    }
}