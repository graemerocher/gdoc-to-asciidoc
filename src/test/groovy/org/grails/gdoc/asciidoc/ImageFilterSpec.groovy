package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by stigmelling on 01.06.16.
 */
class ImageFilterSpec extends Specification {

    void "Test that !! is handled as inline code"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with code !..! formatting"

        def sw = new StringWriter()
        engine.createTemplate('!intropage.png!').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'image::intropage.png[]'
    }
}

