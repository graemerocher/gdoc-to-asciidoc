package org.grails.gdoc.asciidoc.engine.filters

import groovy.transform.CompileStatic
import org.grails.gdoc.asciidoc.engine.NameUtils
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by graemerocher on 02/06/2016.
 */
@CompileStatic
class LinkTestFilter extends RegexTokenFilter {

    final Map<String, String> apiLinks

    LinkTestFilter(Map<String, String> apiLinks) {
        super(/\[(.*?)\]/)
        this.apiLinks = apiLinks
    }

    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        String name = result.group(1)
        String link = name
        if(name.contains('|')) {
            def tokens = name.split(/\|/)
            name = tokens[0].trim()
            link = tokens[1].trim()
            if(link.startsWith("guide:")) {
                link = link.substring(6)
                buffer << "<<$link,$name>>"
            }
            else if(link.startsWith("http://") || link.startsWith("http://")) {
                if(name == link) {
                    buffer << link
                }
                else {
                    buffer << "$link[$name]"
                }
            }
            else if(link.startsWith("api:")) {

                def apiLink = link.substring(4)
                for(prefix in apiLinks.keySet()) {
                    if(apiLink.startsWith(prefix)) {

                        def absolutePath = apiLinks.get(prefix)
                        def fullPath = "$absolutePath${apiLink.replace('.', '/')}.html"
                        buffer << "$fullPath[$name]"

                        break
                    }
                }
            }
            else {
                if(link == name) {
                    buffer << "<<$name>>"
                }
                else {

                    buffer << "<<ref-${NameUtils.getHyphenSeperatedName(link)}-$name,$name>>"
                }
            }
        }
        else {
            if(name ==~ /[a-zA-Z0-9\s]+/) {
                buffer << "<<$name>>"
            }
            else {
                buffer << "[$name]"
            }
        }

    }

}

