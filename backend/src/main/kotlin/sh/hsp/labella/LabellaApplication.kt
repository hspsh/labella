package sh.hsp.labella

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(MainConfiguration::class)
class LabellaApplication

fun main(args: Array<String>) {
    runApplication<LabellaApplication>(*args)
}
