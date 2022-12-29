package sh.hsp.labella.application.services.configuration

import com.sun.istack.logging.Logger
import sh.hsp.labella.model.RuntimeConfiguration
import sh.hsp.labella.model.PrintDimensions

class ConfigurationServiceImpl(
    private val configuration: RuntimeConfiguration
) : ConfigurationService {

    private val logger = Logger.getLogger(ConfigurationServiceImpl::class.java)

    override fun setSize(height: Int, width: Int) {
        configuration.printDimensions = PrintDimensions(width, height)
        logger.info("Size changed to: ${configuration.printDimensions}")
    }

    override fun getSize() = configuration.printDimensions

}