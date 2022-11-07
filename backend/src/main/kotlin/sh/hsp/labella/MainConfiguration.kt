package sh.hsp.labella

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sh.hsp.labella.services.printer.PrinterServiceImpl
import sh.hsp.labella.services.printing.LanguagePrintingService
import sh.hsp.labella.services.printing.PrintingService
import sh.hsp.labella.services.printing.converter.ImageToLanguageImpl
import sh.hsp.labella.services.printing.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printing.converter.zebra.MonoToEpl
import sh.hsp.labella.services.renderer.RendererServiceImpl
import sh.hsp.labella.services.template.MockTemplateService


@Configuration
class MainConfiguration {

    @Bean
    fun printingService(): PrintingService =
        LanguagePrintingService(
            MockTemplateService(),
            RendererServiceImpl(),
            ImageToLanguageImpl(
                SimpleImageToMono(),
                MonoToEpl()
            ),
            PrinterServiceImpl()
        )
}