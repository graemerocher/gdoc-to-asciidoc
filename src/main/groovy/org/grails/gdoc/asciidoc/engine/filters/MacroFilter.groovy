package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.macro.Repository

/**
 * Created by graemerocher on 01/06/2016.
 */
class MacroFilter extends org.radeox.filter.MacroFilter {
    @Override
    Repository getMacroRepository() {
        return super.getMacroRepository()
    }
}
