package org.grails.gdoc.asciidoc.engine.macros

import org.radeox.macro.BaseMacro
import org.radeox.macro.parameter.MacroParameter

/**
 * Created by graemerocher on 02/06/2016.
 */
class NoteMacro extends BaseMacro {
    String getName() {"note"}
    void execute(Writer writer, MacroParameter params) {
        def content = params.content?.trim()
        if(content) {
            writer << "NOTE: ${content}".toString()
        }
    }
}
