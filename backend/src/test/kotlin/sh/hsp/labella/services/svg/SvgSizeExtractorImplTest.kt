package sh.hsp.labella.services.svg

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.services.svg.size.SvgSizeExtractorImpl

class SvgSizeExtractorImplTest {
    @Test
    fun testSizeExtraction() {
        val svgSizeExtractor = SvgSizeExtractorImpl()
        val svg = this.javaClass.getResource("selection.svg").readText()

        val size = svgSizeExtractor.extract(svg)

        assertEquals(PrintDimensions(400, 240), size)
    }

}