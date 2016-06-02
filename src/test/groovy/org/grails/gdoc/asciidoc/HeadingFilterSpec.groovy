package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by ug on 01.06.16.
 */
class HeadingFilterSpec extends Specification {

    void "Test that h1. is translated to one equals sign"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with heading h1. formatting"

        def sw = new StringWriter()
        engine.createTemplate('h1. Very important section').make().writeTo(sw)

        then:"It's a correctly converted header"
        sw.toString() == '''
= Very important section
'''
    }

    void "Test that h2. is translated to two equals signs"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with heading h2. formatting"

        def sw = new StringWriter()
        engine.createTemplate('h2. Very important ' + 'sub' * 2 + 'section').make().writeTo(sw)

        then:"It's a correctly converted header"
        sw.toString() == '''
== Very important subsubsection
'''
    }

    void "Test that h3. is translated to three equals signs"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with heading h3. formatting"

        def sw = new StringWriter()
        engine.createTemplate('h3. Very important ' + 'sub' * 3 + 'section').make().writeTo(sw)

        then:"It's a correctly converted header"
        sw.toString() == '''
=== Very important subsubsubsection
'''
    }

    void "Test that h4. is translated to four equals signs"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with heading h4. formatting"

        def sw = new StringWriter()
        engine.createTemplate('h4. Very important ' + 'sub' * 4 + 'section').make().writeTo(sw)

        then:"It's a correctly converted header"
        sw.toString() == '''
==== Very important subsubsubsubsection
'''
    }

    void "Test that h5. is translated to five equals signs"() {

        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with heading h5. formatting"

        def sw = new StringWriter()
        engine.createTemplate('h5. Very important ' + 'sub' * 5 + 'section').make().writeTo(sw)

        then:"It's a correctly converted header"
        sw.toString() == '''
===== Very important subsubsubsubsubsection
'''
    }
}
