package org.grails.gdoc.asciidoc.engine

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.grails.gdoc.asciidoc.engine.filters.CodeFilter
import org.grails.gdoc.asciidoc.engine.filters.MacroFilter
import org.grails.gdoc.asciidoc.engine.macros.CodeMacro
import org.radeox.engine.BaseRenderEngine

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
@InheritConstructors
class AsciiDocEngine extends BaseRenderEngine {


    @Override
    protected void init() {
        super.init()
        fp.deactivateFilter("org.radeox.filter.MacroFilter")

        def macroFilter = new MacroFilter()
        fp.addFilter(macroFilter)
        macroFilter.macroRepository.put("code", new CodeMacro())
        fp.addFilter(new CodeFilter())
    }
}
