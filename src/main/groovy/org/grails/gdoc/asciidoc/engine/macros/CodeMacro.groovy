package org.grails.gdoc.asciidoc.engine.macros

import groovy.transform.CompileStatic
import org.radeox.macro.LocalePreserved
import org.radeox.macro.parameter.MacroParameter

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class CodeMacro extends LocalePreserved{
    @Override
    void execute(Writer writer, MacroParameter params) throws IllegalArgumentException, IOException {
        if(params.content != null) {
            def lang = "groovy"
            def paramsMap = params.getParams()
            if(!paramsMap.containsKey('title') && paramsMap.containsKey("0")) {
                lang = paramsMap.get("0")
            }
            else if(paramsMap.containsKey("lang")) {
                lang = paramsMap.get("lang")
            }
            writer.write("[source,$lang]")
            if(params.get('title')) {
                writer.write("\n.${params.get('title')}")
            }
            writer.write """
----
${params.content.trim()}
----"""
        }
    }

    @Override
    String getLocaleKey() {
        return "macro.code";
    }
}
