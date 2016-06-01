package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocEngine
import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import org.radeox.example.RadeoxTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 01/06/2016.
 */
class CodeFilterSpec extends Specification {

    void "test radeox to ascii doc template engine"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered"

        def sw = new StringWriter()
        engine.createTemplate('@def foo@').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '`def foo`'
    }
}
