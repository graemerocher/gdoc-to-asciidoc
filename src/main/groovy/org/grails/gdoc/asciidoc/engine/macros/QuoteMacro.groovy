package org.grails.gdoc.asciidoc.engine.macros

import groovy.transform.CompileStatic
import org.radeox.macro.LocalePreserved
import org.radeox.macro.parameter.MacroParameter

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class QuoteMacro extends LocalePreserved {
    @Override
    void execute(Writer writer, MacroParameter params) throws IllegalArgumentException, IOException {
        if(params.content != null) {
            writer.write """____
${params.content}
____"""
        }
    }

    @Override
    String getLocaleKey() {
        return "macro.quote";
    }
}
