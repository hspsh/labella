package sh.hsp.labella.services.template

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SimpleTemplateServiceTest {
    val simpleTemplateService = SimpleTemplateService()
    val SAMPLE_TEMPLATE = """Hello, ${'$'}{MOMA} is ${'$'}{ROMA}"""

    @Test
    fun testListFields() {
        val fields = simpleTemplateService.listFields(SAMPLE_TEMPLATE)

        assertEquals(listOf("MOMA", "ROMA"), fields)
    }

    @Test
    fun testRender() {
        val render = simpleTemplateService.render(
            SAMPLE_TEMPLATE, mapOf(
                Pair("MOMA", "Mom"),
                Pair("ROMA", "DEAD")
            )
        )

        assertEquals("Hello, Mom is DEAD", render)
    }

    @Test
    fun givenSvgWhenTemplatedThenCurledShouldBecomeText() {
        val svg = this.javaClass.getResource("320x224.svg").readText()
        val svgTemplated = this.javaClass.getResource("320x224-templated.svg").readText()

        val templated = simpleTemplateService.render(svg, emptyMap())

        assertEquals(templated, svgTemplated)
    }
}