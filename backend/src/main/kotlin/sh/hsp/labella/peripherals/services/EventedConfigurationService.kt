package sh.hsp.labella.peripherals.services

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import sh.hsp.labella.application.services.configuration.ConfigurationService
import sh.hsp.labella.model.PrintDimensions

class EventedConfigurationService(
    val applicationEventPublisher: ApplicationEventPublisher,
    val configurationService: ConfigurationService
) :
    ConfigurationService by configurationService {


    override fun setSize(height: Int, width: Int) {
        configurationService.setSize(height, width)
        applicationEventPublisher.publishEvent(ConfigurationUpdated(this, configurationService.getSize()))
    }
}

data class ConfigurationUpdated(val currentObject: Any, val printDimensions: PrintDimensions) :
    ApplicationEvent(currentObject)
