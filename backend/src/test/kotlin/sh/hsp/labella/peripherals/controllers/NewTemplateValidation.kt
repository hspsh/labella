package sh.hsp.labella.peripherals.controllers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.http.HttpStatus
import sh.hsp.labella.acceptance.PreviewAcceptanceTest
import sh.hsp.labella.model.Template
import java.awt.image.BufferedImage
import java.io.BufferedReader

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewTemplateValidation {
    @Autowired
    lateinit var rest: TestRestTemplate;

    private val URL = "/api/new/templates"

    @Test
    fun givenEmptyNameTemplateWhenTemplateIsAddedThenFails() {
        val template = createTemplate(name = "")

        val templateResponse =
            rest.postForEntity(
                URL,
                template,
                Nothing::class.java,
                emptyMap<String, String>()
            )
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, templateResponse.statusCode)
    }


    @Test
    fun givenIncorrectTemplateWhenTemplateIsAddedThenFails() {
        val template = createTemplate(contents = "xDDD")

        val templateResponse =
            rest.postForEntity(
                URL,
                template,
                Nothing::class.java,
                emptyMap<String, String>()
            )
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, templateResponse.statusCode)
    }

    companion object {
        fun createTemplate(name: String = "something", contents: String? = null): TemplateCreateDTO {
            return TemplateCreateDTO(
                name,
                Template.TemplateType.SVG,
                contents
                    ?: DefaultResourceLoader().getResource("sh/hsp/labella/acceptance/320x224.svg").inputStream.bufferedReader()
                        .use(BufferedReader::readText)
            )
        }
    }
}