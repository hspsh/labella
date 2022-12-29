package sh.hsp.labella.application.adapters.label.size

import sh.hsp.labella.model.RuntimeConfiguration
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.ports.LabelSizeProvider

class ConfigLabelSizeProvider(
    private val configuration: RuntimeConfiguration
) : LabelSizeProvider {

    override fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions {
        return configuration.printDimensions
    }
}