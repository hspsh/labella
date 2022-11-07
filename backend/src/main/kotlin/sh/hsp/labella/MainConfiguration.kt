package sh.hsp.labella

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.services.printer.LanguagePrinterService
import sh.hsp.labella.services.printer.LanguagePrinterServiceImpl
import sh.hsp.labella.services.printer.converter.ImageToLanguage
import sh.hsp.labella.services.printer.converter.ImageToLanguageImpl
import sh.hsp.labella.services.printer.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printer.converter.zebra.MonoToEpl
import sh.hsp.labella.services.printing.LanguagePrintingService
import sh.hsp.labella.services.renderer.RendererService
import sh.hsp.labella.services.renderer.RendererServiceImpl
import sh.hsp.labella.services.template.SimpleTemplateService
import sh.hsp.labella.services.template.TemplateService
import sh.hsp.labella.services.templating.TemplatingServiceImpl


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
    fun printerService(@Value("\${printerName}") printerName: String): LanguagePrinterServiceImpl {
        return LanguagePrinterServiceImpl(printerName)
    }

    @Bean
    fun imageToLanguage(): ImageToLanguage {
        return ImageToLanguageImpl(
            SimpleImageToMono(),
            MonoToEpl()
        )
    }

    @Bean
    fun languagePrintingService(
        languagePrinterService: LanguagePrinterService,
        imageToLanguage: ImageToLanguage,
        templateService: TemplateService,
        rendererService: RendererService,
        templateRepository: TemplateRepository
    ) {
        LanguagePrintingService(
            templateService,
            rendererService,
            imageToLanguage,
            languagePrinterService,
            templateRepository
        )
    }

    @Bean
    fun templatingService(
        templateRepository: TemplateRepository,
        templateService: TemplateService
    ): TemplatingServiceImpl {
        return TemplatingServiceImpl(templateRepository, templateService)
    }
}