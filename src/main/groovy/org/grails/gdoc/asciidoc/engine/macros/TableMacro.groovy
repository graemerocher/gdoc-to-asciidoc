package org.grails.gdoc.asciidoc.engine.macros

import groovy.transform.CompileStatic
import org.radeox.macro.BaseLocaleMacro
import org.radeox.macro.parameter.MacroParameter
import org.radeox.macro.table.TableBuilder

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class TableMacro extends BaseLocaleMacro{
    @Override
    void execute(Writer writer, MacroParameter params) throws IllegalArgumentException, IOException {

        def content = params.content
        if(content != null) {

            def lines = content.split(/\n/)
            content = lines.collect() { String str -> str.split(/\|/).collect() { String str2 -> str2.trim() } .join(',')}.join('\n')
            writer.write """[format="csv", options="header"]
|===
$content
|==="""
        }
    }

    String getLocaleKey() {
        return "macro.table"
    }
}
