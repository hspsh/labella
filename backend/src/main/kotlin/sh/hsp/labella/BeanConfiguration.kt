package sh.hsp.labella

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sh.hsp.labella.application.adapters.printer.LpCliLanguagePrinterService
import sh.hsp.labella.application.adapters.printer.converter.ImageToLanguageImpl
import sh.hsp.labella.application.adapters.printer.converter.mono.SimpleImageToMono
import sh.hsp.labella.application.adapters.printer.converter.zebra.MonoToEpl
import sh.hsp.labella.application.adapters.svg.FlavorAwareRenderingService
import sh.hsp.labella.application.adapters.svg.InkscapeRendererService
import sh.hsp.labella.application.adapters.svg.MultipleSVGRenderingServiceImpl
import sh.hsp.labella.application.adapters.svg.flavor.SVGFlavor
import sh.hsp.labella.application.adapters.svg.flavor.flavors.QRCodeFlavor
import sh.hsp.labella.application.adapters.svg.size.SvgSizeExtractorImpl
import sh.hsp.labella.application.adapters.template.JinjaTemplateService
import sh.hsp.labella.application.services.previewing.CachedPreviewingService
import sh.hsp.labella.application.services.previewing.LanguagePreviewingService
import sh.hsp.labella.application.services.previewing.PreviewingService
import sh.hsp.labella.application.services.printing.LanguagePrintingService
import sh.hsp.labella.application.services.printing.PrintingService
import sh.hsp.labella.application.services.templating.TemplatingService
import sh.hsp.labella.application.services.templating.TemplatingServiceImpl
import sh.hsp.labella.model.ports.*
import sh.hsp.labella.peripherals.adapters.TemplateRepository


@Configuration
class BeanConfiguration {

    @Bean
    fun printingService(
        languagePrinterService: LanguagePrinterService,
        imageToLanguage: ImageToLanguage,
        previewingService: PreviewingService
    ): PrintingService =
        LanguagePrintingService(
            previewingService,
            imageToLanguage,
            languagePrinterService
        )

    @Bean
    fun previewingService(
        templatingService: TemplatingService,
        svgSizeExtractor: SvgSizeExtractor,
        multipleSVGRendererService: MultipleSVGRenderingService,
        labelSizeProvider: LabelSizeProvider,
        templateRepository: TemplateRepository
    ): PreviewingService =
        CachedPreviewingService(
            LanguagePreviewingService(
                templatingService,
                svgSizeExtractor,
                multipleSVGRendererService,
                labelSizeProvider
            )
        )

    @Bean
    fun templatingService(
        templateRepository: TemplateRepository,
        templateService: TemplateService
    ): TemplatingService {
        return TemplatingServiceImpl(templateRepository, templateService)
    }

    @Bean
    fun templateService(): TemplateService {
        return JinjaTemplateService()
    }

    @Bean
    fun multipleSVGRendererService(): MultipleSVGRenderingService {
        return MultipleSVGRenderingServiceImpl(
            FlavorAwareRenderingService(
                InkscapeRendererService(), listOf<SVGFlavor>(
                    QRCodeFlavor()
                )
            )
        )
    }

    @Bean
    fun printerService(@Value("\${printerName}") printerName: String): LanguagePrinterService {
        return LpCliLanguagePrinterService(printerName)
    }

    @Bean
    fun imageToLanguage(): ImageToLanguage {
        return ImageToLanguageImpl(
            SimpleImageToMono(),
            MonoToEpl()
        )
    }

    @Bean
    fun svgSizeExtractor() =
        SvgSizeExtractorImpl()

    @Bean
    fun labelSizeProvider(
        @Value("\${label.width}") width: Int,
        @Value("\${label.height}") height: Int
    ) =
        sh.hsp.labella.application.adapters.label.FixedLabelSizeProvider(width, height)
}