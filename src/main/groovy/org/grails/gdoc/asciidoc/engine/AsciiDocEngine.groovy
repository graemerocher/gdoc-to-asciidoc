package org.grails.gdoc.asciidoc.engine

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.grails.gdoc.asciidoc.engine.filters.CodeFilter
import org.grails.gdoc.asciidoc.engine.filters.HeadingFilter
import org.grails.gdoc.asciidoc.engine.filters.MacroFilter
import org.grails.gdoc.asciidoc.engine.macros.CodeMacro
import org.grails.gdoc.asciidoc.engine.macros.QuoteMacro
import org.grails.gdoc.asciidoc.engine.macros.TableMacro
import org.radeox.engine.BaseRenderEngine
import org.radeox.filter.FilterPipe

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
@InheritConstructors
class AsciiDocEngine extends BaseRenderEngine {


    @Override
    protected void init() {
        fp = new FilterPipe(initialContext)

        def macroFilter = new MacroFilter()
        fp.addFilter(macroFilter)
        macroFilter.macroRepository.put("table", new TableMacro())
        macroFilter.macroRepository.put("code", new CodeMacro())
        macroFilter.macroRepository.put("quote", new QuoteMacro())
        fp.addFilter(new CodeFilter())
        fp.addFilter(new HeadingFilter())
    }
}
