package sh.hsp.labella.application.ports

import sh.hsp.labella.model.Template
import java.util.*

fun interface TemplateRepository {
    fun findById(id: Long): Template?
}