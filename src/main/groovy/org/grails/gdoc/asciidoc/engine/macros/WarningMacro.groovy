package org.grails.gdoc.asciidoc.engine.macros

import groovy.transform.CompileStatic
import org.radeox.macro.BaseMacro
import org.radeox.macro.parameter.MacroParameter

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class WarningMacro extends BaseMacro {
    String getName() {"warning"}
    void execute(Writer writer, MacroParameter params) {
        def content = params.content
        if(content) {
            writer << "WARNING: ${ content}".toString()
        }
    }
}
