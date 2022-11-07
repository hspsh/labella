package sh.hsp.labella

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sh.hsp.labella.services.printer.PrinterServiceImpl
import sh.hsp.labella.services.printing.LanguagePrintingService
import sh.hsp.labella.services.printing.PrintingService
import sh.hsp.labella.services.printing.converter.ImageToLanguageImpl
import sh.hsp.labella.services.printing.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printing.converter.zebra.MonoToEpl
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.renderer.RendererServiceImpl
import sh.hsp.labella.services.template.MockTemplateService
import sh.hsp.labella.services.template.SimpleTemplateService
import sh.hsp.labella.services.template.TemplateService


@Configuration
class MainConfiguration {

    @Bean
    fun templateService(): TemplateService {
        return SimpleTemplateService()
    }

    @Bean
    fun rendererService(): RendererService {
        return RendererServiceImpl()
    }

    @Bean
    fun printingService(
        @Value("\${printerName}") printerName: String,
        templateService: TemplateService,
        rendererService: RendererService
    ): PrintingService =
        LanguagePrintingService(
            templateService,
            rendererService,
            ImageToLanguageImpl(
                SimpleImageToMono(),
                MonoToEpl()
            ),
            PrinterServiceImpl(printerName)
        )
}