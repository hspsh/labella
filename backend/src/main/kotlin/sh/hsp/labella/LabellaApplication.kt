package sh.hsp.labella

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import sh.hsp.labella.peripherals.InfraConfiguration

@SpringBootApplication
@Import(InfraConfiguration::class, BeanConfiguration::class)
class LabellaApplication

fun main(args: Array<String>) {
    runApplication<LabellaApplication>(*args)
}
