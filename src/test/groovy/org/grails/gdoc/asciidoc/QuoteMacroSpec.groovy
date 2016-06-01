package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 01/06/2016.
 */
class QuoteMacroSpec extends Specification{
    void "Test that {quote} macro produces block quotes"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {quote} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
{quote}
Hello World
{quote}
'''.trim()).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''____
Hello World
____'''
    }
}
