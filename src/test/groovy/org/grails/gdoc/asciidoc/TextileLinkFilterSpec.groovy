package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by ug on 01.06.16.
 */
class TextileLinkFilterSpec extends Specification {

    void "Test if links are formatted to asciidoc links"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with a hyperlink"

        def sw = new StringWriter()
        engine.createTemplate('"gr8conf.eu":http://gr8conf.eu/#/ ').make().writeTo(sw)

        then:"It's a correctly converted hyperlink"
        sw.toString() == 'http://gr8conf.eu/#/[gr8conf.eu] '

    }
}
