package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 02/06/2016.
 */
class TextileLinkFilterSpec extends Specification {

    void "Test absolute textile links are converted to asciidoc links"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template with a text link"

        def sw = new StringWriter()
        engine.createTemplate('My "Foo":http://foo.com Link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My http://foo.com[Foo] Link'
    }

    void "Test relative textile links are converted to asciidoc links"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template with a text link"

        def sw = new StringWriter()
        engine.createTemplate('My "Foo":index.html Link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My link:index.html[Foo] Link'
    }
}