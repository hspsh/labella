package sh.hsp.labella.acceptance

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.http.HttpStatus
import sh.hsp.labella.model.Template
import sh.hsp.labella.services.printer.LanguagePrinterService
import sh.hsp.labella.services.renderer.imageEqual
import java.awt.image.BufferedImage
import java.io.BufferedReader
import javax.imageio.ImageIO

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PreviewAcceptanceTest {
    @Autowired
    lateinit var rest: TestRestTemplate;

    @MockBean // So the test will work even if you ain't have printer defined
    lateinit var printerService: LanguagePrinterService

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