package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 02/06/2016.
 */
class LinkFilterSpec extends Specification {

    void "Test links [] create api references correctly"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with a [..] link"

        def sw = new StringWriter()
        engine.createTemplate('My [Entity|api:grails.persistence.Entity] link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My http://docs.grails.org/latest/api/grails/persistence/Entity.html[Entity] link'
    }

    void "Test links [] create internal cross references"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with a [..] link"

        def sw = new StringWriter()
        engine.createTemplate('My [Foo] link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My <<Foo>> link'
    }

    void "Test links [] create internal cross references with alias"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with an aliased [..] link "

        def sw = new StringWriter()
        engine.createTemplate('My [Foo|foo-section] link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My <<foo-section,Foo>> link'
    }

    void "Test links [] create internal cross references with alias and guide: prefix"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with an aliased [..] link "

        def sw = new StringWriter()
        engine.createTemplate('My [Foo|guide:foo-section] link').make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == 'My <<foo-section,Foo>> link'
    }
}
