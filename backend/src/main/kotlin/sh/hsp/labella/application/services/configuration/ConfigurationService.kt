package sh.hsp.labella.application.services.configuration

import sh.hsp.labella.model.PrintDimensions

interface ConfigurationService {
    fun setSize(height: Int, width: Int)
    fun getSize(): PrintDimensions
}