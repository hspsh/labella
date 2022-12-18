package sh.hsp.labella.acceptance

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import sh.hsp.labella.peripherals.controllers.PrintDTO
import sh.hsp.labella.model.Template
import sh.hsp.labella.model.ports.LanguagePrinterService
import java.io.BufferedReader

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PrintingAcceptanceTest {
    @Autowired
    lateinit var rest: TestRestTemplate;

    @MockBean // So the test will work even if you ain't have printer defined
    lateinit var printerService: LanguagePrinterService

    @Test
    fun whenTemplateIsAddedAndPrintedThenSucceeds() {
        val template = createTemplate()

        val templateResponse =
            rest.postForEntity("/api/templates", template, IdOnly::class.java, emptyMap<String, String>())
        val id = templateResponse.body!!.id
        Assertions.assertEquals(HttpStatus.CREATED, templateResponse.statusCode)

        val printingResponse =
            rest.postForEntity(
                "/api/templates/${id}/print",
                PrintDTO(emptyMap()),
                Nothing::class.java,
                emptyMap<String, String>()
            )
        Assertions.assertEquals(HttpStatus.OK, printingResponse.statusCode)
    }

    fun createTemplate(): Template {
        val template = Template()
        template.name = "Something"
        template.type = Template.TemplateType.SVG
        template.template =
            this.javaClass.getResource("320x224.svg").openStream().bufferedReader()
                .use(BufferedReader::readText)
        return template
    }

    data class IdOnly(val id: Long);
}