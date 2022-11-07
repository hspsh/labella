package sh.hsp.labella.services.templating

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.services.template.TemplateService

class TemplatingServiceImpl(
    val templateRepository: TemplateRepository,
    val templateService: TemplateService
) : TemplatingService {
    override fun fields(templateId: Long): List<String> {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        return template.fields { contents -> templateService.listFields(contents) }
    }
}