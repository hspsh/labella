package sh.hsp.labella

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.filter.ShallowEtagHeaderFilter
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.infra.BufferedImageDeserializer
import sh.hsp.labella.infra.BufferedImageSerializer
import sh.hsp.labella.model.*
import sh.hsp.labella.services.previewing.CachedPreviewingService
import sh.hsp.labella.services.previewing.LanguagePreviewingService
import sh.hsp.labella.services.previewing.PreviewingService
import sh.hsp.labella.services.printer.LpCliLanguagePrinterService
import sh.hsp.labella.services.printer.converter.ImageToLanguageImpl
import sh.hsp.labella.services.printer.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printer.converter.zebra.MonoToEpl
import sh.hsp.labella.services.printing.LanguagePrintingService
import sh.hsp.labella.services.printing.PrintingService
import sh.hsp.labella.services.renderer.svg.FlavorAwareRenderingService
import sh.hsp.labella.services.renderer.svg.InkscapeRendererService
import sh.hsp.labella.services.renderer.svg.MultipleSVGRenderingServiceImpl
import sh.hsp.labella.services.svg.flavor.SVGFlavor
import sh.hsp.labella.services.svg.flavor.flavors.QRCodeFlavor
import sh.hsp.labella.services.svg.size.SvgSizeExtractorImpl
import sh.hsp.labella.services.template.SimpleTemplateService
import sh.hsp.labella.services.templating.TemplatingServiceImpl
import java.awt.image.BufferedImage
import javax.servlet.Filter


@Configuration
class BeanConfiguration {

    @Bean
    fun templateService(): TemplateService {
        return SimpleTemplateService()
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
        templateService: TemplateService,
        svgSizeExtractor: SvgSizeExtractor,
        multipleSVGRendererService: MultipleSVGRenderingService,
        templateRepository: TemplateRepository
    ): PreviewingService =
        CachedPreviewingService(
            LanguagePreviewingService(
                templateService,
                svgSizeExtractor,
                multipleSVGRendererService,
                templateRepository
            )
        )

    @Bean
    fun templatingService(
        templateRepository: TemplateRepository,
        templateService: TemplateService
    ): TemplatingServiceImpl {
        return TemplatingServiceImpl(templateRepository, templateService)
    }
}