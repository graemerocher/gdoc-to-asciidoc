package org.grails.gdoc.asciidoc

import org.grails.gdoc.asciidoc.engine.AsciiDocTemplateEngine
import spock.lang.Specification

/**
 * Created by graemerocher on 01/06/2016.
 */
class TableMacroSpec extends Specification {

    void "Test that {table} macro produces correct tables"() {
        given:"A template engine"
        def engine = new AsciiDocTemplateEngine()

        when:"A template is rendered with {table} formatting"

        def sw = new StringWriter()

        def str = '''\
{table}
HTTP Method | URI               | Grails Action
GET         | /books            | index
GET         | /books/create     | create
{table}'''
        str.eachLine { println it}
        engine.createTemplate(str).make().writeTo(sw)

        then:"The output is correct"
        sw.toString() == '''[format="csv", options="header"]
|===

HTTP Method,URI,Grails Action
GET,/books,index
GET,/books/create,create
|==='''
    }
}
