package sh.hsp.labella.model.ports

import sh.hsp.labella.model.PrintDimensions

interface LabelRescaler {
    fun rescale(original: PrintDimensions): PrintDimensions
}