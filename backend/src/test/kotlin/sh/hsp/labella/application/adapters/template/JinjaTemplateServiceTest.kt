package sh.hsp.labella.application.adapters.template

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JinjaTemplateServiceTest {
    val randomString = "HELLO {{name}}!"
    val jinjaTemplateService = JinjaTemplateService()

    @Test
    fun whenTemplatedThenCorrect() {

        val output = jinjaTemplateService.render(randomString, mapOf(Pair("name", "LOSER")))

        assertEquals(output, "HELLO LOSER!")
    }


    @Test
    fun whenListedFieldsThenCorrect() {

        val output = jinjaTemplateService.listFields(randomString)

        assertEquals(output, setOf("name"))
    }

}