package sh.hsp.labella.peripherals.adapters

import org.springframework.stereotype.Component
import sh.hsp.labella.application.ports.TemplateRepository
import sh.hsp.labella.model.Template
import kotlin.jvm.optionals.getOrNull

@Component
class TemplateRepositoryImpl(val springTemplateRepository: SpringTemplateRepository) : TemplateRepository {
    @OptIn(ExperimentalStdlibApi::class)
    override fun findById(id: Long): Template? = springTemplateRepository.findById(id).getOrNull()
}