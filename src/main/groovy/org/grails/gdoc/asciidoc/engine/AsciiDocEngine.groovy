package org.grails.gdoc.asciidoc.engine

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.grails.gdoc.asciidoc.engine.filters.CodeFilter
import org.grails.gdoc.asciidoc.engine.filters.HeadingFilter
import org.grails.gdoc.asciidoc.engine.filters.LinkTestFilter
import org.grails.gdoc.asciidoc.engine.filters.MacroFilter
import org.grails.gdoc.asciidoc.engine.filters.TextileLinkFilter
import org.grails.gdoc.asciidoc.engine.macros.CodeMacro
import org.grails.gdoc.asciidoc.engine.macros.NoteMacro
import org.grails.gdoc.asciidoc.engine.macros.QuoteMacro
import org.grails.gdoc.asciidoc.engine.macros.TableMacro
import org.grails.gdoc.asciidoc.engine.macros.WarningMacro
import org.radeox.engine.BaseRenderEngine
import org.radeox.filter.FilterPipe

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
@InheritConstructors
class AsciiDocEngine extends BaseRenderEngine {

    public static final LinkedHashMap<String, String> DEFAULT_API_LINKS = [
            'org.springframework.boot': 'http://docs.spring.io/spring-boot/docs/current/api/',
            'org.springframework'     : "http://docs.spring.io/spring/docs/current/javadoc-api/",
            'org.hibernate'           : 'http://docs.jboss.org/hibernate/orm/current/javadocs/',
            'java.'                   : 'https://docs.oracle.com/javase/8/docs/api/',
            'javax.'                  : 'https://docs.oracle.com/javaee/7/api/',
            'groovy.'                 : 'http://docs.groovy-lang.org/docs/latest/html/api/',
            'org.codehaus.groovy.'    : 'http://docs.groovy-lang.org/docs/latest/html/api/',
            'grails.gorm'             : 'http://gorm.grails.org/latest/api/',
            'grails.orm'              : 'http://gorm.grails.org/latest/api/',
            'org.grails.datastore'    : 'http://gorm.grails.org/latest/api/',
            'org.grails.orm'          : 'http://gorm.grails.org/latest/api/',
            'grails.'                 : 'http://docs.grails.org/latest/api/',
            'org.grails.'             : 'http://docs.grails.org/latest/api/'
    ]

    final Map<String, String> apiLinks

    AsciiDocEngine() {
        this.apiLinks = [:]
    }

    AsciiDocEngine(Map<String, String> apiLinks ) {
        this.apiLinks = new LinkedHashMap<>(apiLinks)
        this.apiLinks.putAll(DEFAULT_API_LINKS)
    }

    @Override
    protected void init() {
        fp = new FilterPipe(initialContext)

        def macroFilter = new MacroFilter()
        fp.addFilter(new LinkTestFilter(apiLinks))
        fp.addFilter(macroFilter)
        macroFilter.macroRepository.put("table", new TableMacro())
        macroFilter.macroRepository.put("code", new CodeMacro())
        macroFilter.macroRepository.put("note", new NoteMacro())
        macroFilter.macroRepository.put("quote", new QuoteMacro())
        macroFilter.macroRepository.put("warning", new WarningMacro())
        fp.addFilter(new CodeFilter())
        fp.addFilter(new HeadingFilter())
        fp.addFilter(new TextileLinkFilter())

    }
}
