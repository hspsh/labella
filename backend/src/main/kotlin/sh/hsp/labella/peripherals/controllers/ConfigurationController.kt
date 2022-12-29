package sh.hsp.labella.peripherals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.application.services.configuration.ConfigurationService

@RestController
@RequestMapping(path = ["/api/configuration"])
@CrossOrigin(origins = ["*"])
class ConfigurationController(
    val configurationService: ConfigurationService
) {

    @PostMapping(path = ["/size"])
    @ResponseStatus(code = HttpStatus.OK)
    fun changeSize(@RequestBody dimensionsDTO: DimensionsDTO) {
        configurationService.setSize(dimensionsDTO.height, dimensionsDTO.width)
    }

    @GetMapping(path = ["/size"])
    fun size(): DimensionsDTO {
        return configurationService.getSize().let {
            DimensionsDTO(it.xInPixels, it.yInPixel)
        }
    }
}

data class DimensionsDTO(val width: Int, val height: Int)