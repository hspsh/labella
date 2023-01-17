package sh.hsp.labella.peripherals.controllers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.data.domain.Page
import org.springframework.http.*
import sh.hsp.labella.acceptance.PreviewAcceptanceTest
import sh.hsp.labella.model.Template
import java.io.BufferedReader
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemplateValidation {
    @Autowired
    lateinit var rest: TestRestTemplate;

    // VALIDATION

    @Test
    fun givenEmptyNameTemplateWhenTemplateIsAddedThenFails() {
        val template = postTemplate(name = "")

        val templateResponse =
            rest.postForEntity(
                "/api/templates",
                template,
                PreviewAcceptanceTest.IdOnly::class.java,
                emptyMap<String, String>()
            )
        val id = templateResponse.body!!.id
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, templateResponse.statusCode)
    }


    @Test
    fun givenIncorrectTemplateWhenTemplateIsAddedThenFails() {
        val template = postTemplate(contents = "xDDD")

        val templateResponse = rest.postTemplate(template)

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, templateResponse?.statusCode)
    }

    // SEARCH

    @Test
    fun givenFewTemplatesWhenSearchedThenReturnedCorrectly() {
        listOf(
            postTemplate(name = "Rysunek Psa"),
            postTemplate(name = "Rysunek Kota"),
            postTemplate(name = "Zdjęcie Psa")
        ).forEach { rest.postTemplate(it) }


        val headers = HttpHeaders().apply {
            accept = listOf(MediaType.APPLICATION_JSON)
        }
        val httpEntity = HttpEntity<Map<String, String>>(headers)
        val templateResponse = rest.exchange("/api/templates", HttpMethod.GET, httpEntity, Map::class.java)

        Assertions.assertEquals(
            2,
            templateResponse.body?.size
        )
    }


    companion object {
        fun TestRestTemplate.postTemplate(template: Template): ResponseEntity<PreviewAcceptanceTest.IdOnly> =
            this.postForEntity(
                "/api/templates",
                template,
                PreviewAcceptanceTest.IdOnly::class.java,
                emptyMap<String, String>()
            )


        fun postTemplate(name: String = "something", contents: String? = null): Template {
            val template = Template()
            template.name = name
            template.type = Template.TemplateType.SVG
            template.template =
                contents
                    ?: DefaultResourceLoader().getResource("sh/hsp/labella/acceptance/320x224.svg").inputStream.bufferedReader()
                        .use(BufferedReader::readText)
            return template
        }
    }
}