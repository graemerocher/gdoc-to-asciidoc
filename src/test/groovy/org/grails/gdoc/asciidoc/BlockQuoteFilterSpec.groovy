package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

class BlockQuoteFilterSpec extends Specification {

    void "Test that bc. is handled as literal block"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with code bc. formatting"
        String template = """before

bc.
text

after
"""

        def sw = new StringWriter()
        engine.createTemplate(template).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == """before

----
text
----

after
"""
    }
}
