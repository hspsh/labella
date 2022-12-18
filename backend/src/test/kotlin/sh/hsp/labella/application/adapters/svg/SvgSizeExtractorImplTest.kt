package sh.hsp.labella.application.adapters.svg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.hsp.labella.application.adapters.svg.size.SvgSizeExtractorImpl
import sh.hsp.labella.model.PrintDimensions

class SvgSizeExtractorImplTest {
    @Test
    fun testSizeExtraction() {
        val svgSizeExtractor = SvgSizeExtractorImpl()
        val svg = this.javaClass.getResource("selection.svg").readText()

        val size = svgSizeExtractor.extract(svg)

        assertEquals(PrintDimensions(400, 240), size)
    }

}