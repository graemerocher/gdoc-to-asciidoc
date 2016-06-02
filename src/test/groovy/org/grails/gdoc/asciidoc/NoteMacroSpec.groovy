package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 02/06/2016.
 */
class NoteMacroSpec extends Specification {
    void "Test the {warning} macro renders warnings"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {note} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''\
{warning}
My Note
{warning}''').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'WARNING: My Note'
    }

    void "Test the {note} macro renders notes"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {note} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''\
{note}
My Note
{note}''').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'NOTE: My Note'
    }
}
