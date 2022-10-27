package sh.hsp.labella

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LabellaApplication

fun main(args: Array<String>) {
	runApplication<LabellaApplication>(*args)
}
