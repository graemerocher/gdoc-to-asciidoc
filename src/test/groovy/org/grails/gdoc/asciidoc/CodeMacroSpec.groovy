package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by graemerocher on 01/06/2016.
 */
class CodeMacroSpec extends Specification {
//    @Ignore
    void "test {code} macro that uses wiki syntax within code blocks"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {code} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
Here is an example:

{code:xml}
<cache:render template="myTemplate" model="[user: currentUser]" key="${currentUser.id}"/>
{code}
'''.trim()).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''Here is an example:

[source,xml]
----
<cache:render template="myTemplate" model="[user: currentUser]" key="${currentUser.id}"/>
----'''
    }

    void "Test that {code} macro is used with a title specified"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {code} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
{code:title=Test.groovy}
def foo = "bar"
{code}
'''.trim()).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''[source,groovy]
.Test.groovy
----
def foo = "bar"
----'''
    }

    void "Test that {code} macro is used with a language specified"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {code} formatting"

        def sw = new StringWriter()
        engine.createTemplate('''
{code:java}
def foo = "bar"
{code}
'''.trim()).make().writeTo(sw)

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
'''.trim()).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''[source,groovy]
----
def foo = "bar"
----'''
    }
}
