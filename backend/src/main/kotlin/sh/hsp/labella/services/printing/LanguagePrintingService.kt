package sh.hsp.labella.services.printing

import sh.hsp.labella.services.previewing.PreviewingService
import sh.hsp.labella.model.LanguagePrinterService
import sh.hsp.labella.model.ImageToLanguage

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