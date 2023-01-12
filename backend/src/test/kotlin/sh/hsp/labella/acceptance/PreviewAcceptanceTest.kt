package sh.hsp.labella.acceptance

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.http.HttpStatus
import sh.hsp.labella.peripherals.controllers.PreviewController.ImagesDTO
import sh.hsp.labella.model.Template
import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.util.stream.Collectors

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PreviewAcceptanceTest {
    @Autowired
    lateinit var rest: TestRestTemplate;

    @Test
    fun whenTemplateIsAddedAndPreviewedThenSucceeds() {
        val template = createTemplate()
//        val outputImage =
//            ImageIO.read(DefaultResourceLoader().getResource("sh/hsp/labella/acceptance/320x224.png").inputStream)

        val templateResponse =
            rest.postForEntity("/api/templates", template, IdOnly::class.java, emptyMap<String, String>())
        val id = templateResponse.body!!.id
        Assertions.assertEquals(HttpStatus.CREATED, templateResponse.statusCode)

        val printingResponse =
            rest.getForEntity(
                "/api/templates/${id}/preview",
                BufferedImage::class.java
            )
        Assertions.assertEquals(HttpStatus.OK, printingResponse.statusCode)
        // Assertions.assertTrue(outputImage.imageEqual(outputImage.body!!))
    }

    @Test
    fun whenTemplateIsAddedAndPreviewedAllThenSucceeds() {
        val template = createTemplate()
//        val outputImage =
//            ImageIO.read(DefaultResourceLoader().getResource("sh/hsp/labella/acceptance/320x224.png").inputStream)

        val templateResponse =
            rest.postForEntity("/api/templates", template, IdOnly::class.java, emptyMap<String, String>())
        val id = templateResponse.body!!.id
        Assertions.assertEquals(HttpStatus.CREATED, templateResponse.statusCode)

        val printingResponse =
            rest.getForEntity(
                "/api/templates/${id}/preview",
                ImagesDTO::class.java
            )
        Assertions.assertEquals(HttpStatus.OK, printingResponse.statusCode)
        // Assertions.assertTrue(outputImage.imageEqual(outputImage.body!!))
    }

    fun createTemplate(): Template {
        val template = Template()
        template.name = "Something"
        template.type = Template.TemplateType.SVG
        template.template =
            DefaultResourceLoader().getResource("sh/hsp/labella/acceptance/320x224.svg").inputStream.bufferedReader()
                .use(BufferedReader::readText)
        return template
    }

    data class IdOnly(val id: Long);
}