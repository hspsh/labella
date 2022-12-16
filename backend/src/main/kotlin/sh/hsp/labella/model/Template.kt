package sh.hsp.labella.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.awt.image.BufferedImage
import java.util.*
import java.util.function.Function
import javax.persistence.*

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

    fun render(
        fields: Map<String, String>?,
        templateService: TemplateService,
        svgSizeExtractor: SvgSizeExtractor,
        SVGRenderer: MultipleSVGRenderingService
    ): List<RenderingOutput> {
        if (type != TemplateType.SVG) {
            throw UnsupportedOperationException()
        }

        val templated = templateService.render(template, fields ?: emptyMap())
        val printDimensions = svgSizeExtractor.extract(templated) ?: PrintDimensions.ORANGE_LABEL
        return SVGRenderer.renderAll(RenderingInput.SVGRenderingInput(templated, printDimensions))
    }

    fun fields(fieldExtractor: Function<String, List<String>>): List<String> =
        fieldExtractor.apply(template)


    enum class TemplateType {
        SVG, MD
    }
}

data class PrintDimensions(val xInPixels: Int, val yInPixel: Int) {
    companion object {
        val ORANGE_LABEL = PrintDimensions(400, 240)
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
    data class MdRenderingInput(val content: String, val printDimensions: PrintDimensions) : RenderingInput()
}