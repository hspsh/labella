package sh.hsp.labella.model

import com.fasterxml.jackson.annotation.JsonProperty
import sh.hsp.labella.model.ports.*
import java.awt.image.BufferedImage
import java.util.*
import java.util.function.Function
import javax.persistence.*
import kotlin.math.min
import kotlin.math.roundToInt

@Entity
@Table(name = "templates")
class Template {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "type")
    var type: TemplateType = TemplateType.SVG

    @Column(name = "template")
    var template: String = ""

    @Column(name = "created")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var created: Date? = null

    @Column(name = "updated")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var updated: Date? = null

    @PrePersist
    fun onCreate() {
        updated = Date()
        created = Date()
    }

    @PreUpdate
    fun onUpdate() {
        updated = Date()
    }

    fun template(
        fields: Map<String, String>?,
        templateService: TemplateService,
    ): Templated {
        if (type != TemplateType.SVG) {
            throw UnsupportedOperationException()
        }

        return Templated(templateService.render(template, fields ?: emptyMap()))
    }

    fun fields(fieldExtractor: Function<String, List<String>>): List<String> =
        fieldExtractor.apply(template)


    enum class TemplateType {
        SVG, MD
    }
}

data class Templated(val templated: String) {
    val DEFAULT_LABEL_SIZE = PrintDimensions.ORANGE_LABEL

    fun render(
        svgSizeExtractor: SvgSizeExtractor,
        labelSizeProvider: LabelSizeProvider,
        SVGRenderer: MultipleSVGRenderingService
    ): List<RenderingOutput> {
        val svgDimensions = svgSizeExtractor.extract(templated) ?: DEFAULT_LABEL_SIZE
        val labelSize = labelSizeProvider.provideLabelSize(svgDimensions)
        val toRenderDimensions = svgDimensions.rescale(labelSize)
        return SVGRenderer.renderAll(RenderingInput.SVGRenderingInput(templated, toRenderDimensions))
    }

    fun PrintDimensions.rescale(to: PrintDimensions): PrintDimensions {
        val xScale = to.xInPixels.toFloat() / this.xInPixels
        val yScale = to.yInPixel.toFloat() / this.yInPixel

        val scale = min(xScale, yScale)

        return PrintDimensions(
            (this.xInPixels * scale).roundToInt(),
            (this.yInPixel * scale).roundToInt()
        )
    }
}

data class RenderingOutput(val image: BufferedImage) {
    fun printViaLanguage(
        imageToLanguageConverter: ImageToLanguage,
        languagePrinter: LanguagePrinterService
    ) {
        val language = imageToLanguageConverter.convert(image)
        languagePrinter.print(language)
    }
}

sealed class RenderingInput {
    data class SVGRenderingInput(val content: String, val printDimensions: PrintDimensions) : RenderingInput()
}

data class PrintDimensions(val xInPixels: Int, val yInPixel: Int) {
    companion object {
        val ORANGE_LABEL = PrintDimensions(400, 240)
    }
}