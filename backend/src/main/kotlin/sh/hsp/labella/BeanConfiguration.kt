package sh.hsp.labella

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sh.hsp.labella.application.adapters.label.LabelFittingRescaler
import sh.hsp.labella.application.adapters.label.size.ConfigLabelSizeProvider
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
import sh.hsp.labella.application.ports.TemplateRepository
import sh.hsp.labella.application.services.configuration.ConfigurationService
import sh.hsp.labella.application.services.configuration.ConfigurationServiceImpl
import sh.hsp.labella.application.services.previewing.LanguagePreviewingService
import sh.hsp.labella.application.services.previewing.PreviewingService
import sh.hsp.labella.application.services.printing.LanguagePrintingService
import sh.hsp.labella.application.services.printing.PrintingService
import sh.hsp.labella.application.services.templating.TemplatingService
import sh.hsp.labella.application.services.templating.TemplatingServiceImpl
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.RuntimeConfiguration
import sh.hsp.labella.model.ports.*
import sh.hsp.labella.peripherals.controllers.SpringTemplateRepository
import sh.hsp.labella.peripherals.services.CachedPreviewingService


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
        labelRescaler: LabelRescaler,
        templateRepository: SpringTemplateRepository
    ): PreviewingService =
        CachedPreviewingService(
            LanguagePreviewingService(
                templatingService,
                svgSizeExtractor,
                multipleSVGRendererService,
                labelRescaler
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
    fun configurationService(
        configuration: RuntimeConfiguration
    ): ConfigurationService = ConfigurationServiceImpl(configuration)

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
    fun labelRescaler(
        labelSizeProvider: LabelSizeProvider
    ): LabelRescaler =
        LabelFittingRescaler(labelSizeProvider)

    @Bean
    fun svgSizeExtractor() =
        SvgSizeExtractorImpl()

    @Bean
    fun configuration(
        @Value("\${label.width}") width: Int,
        @Value("\${label.height}") height: Int
    ) =
        RuntimeConfiguration(
            PrintDimensions(width, height)
        )

    @Bean
    fun labelSizeProvider(
        configuration: RuntimeConfiguration
    ): LabelSizeProvider =
        ConfigLabelSizeProvider(configuration)
}