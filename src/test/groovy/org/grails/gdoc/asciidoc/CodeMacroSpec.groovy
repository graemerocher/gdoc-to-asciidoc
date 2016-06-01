package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 01/06/2016.
 */
class CodeMacroSpec extends Specification {

    void "Test that {code} macro is used with a language specified"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {code} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
{code:java}
def foo = "bar"
{code}
''').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''[source,java]
----
def foo = "bar"
----'''
    }

    void "Test that {code} macro is used"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {code} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
{code}
def foo = "bar"
{code}
''').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''[source,groovy]
----
def foo = "bar"
----'''
    }
}
