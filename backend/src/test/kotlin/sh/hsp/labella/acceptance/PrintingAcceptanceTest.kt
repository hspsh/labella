package sh.hsp.labella.acceptance

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PrintingAcceptanceTest {
    @Autowired
    lateinit var rest: TestRestTemplate;

    @Test
    fun whenTemplateIsAddedAndPrintedThenSucceeds() {

    }
}