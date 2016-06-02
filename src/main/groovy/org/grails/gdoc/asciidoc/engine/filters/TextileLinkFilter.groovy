package org.grails.gdoc.asciidoc.engine.filters

import groovy.transform.CompileStatic
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by graemerocher on 02/06/2016.
 */
@CompileStatic
class TextileLinkFilter extends RegexTokenFilter {
    TextileLinkFilter() {
        super(/"([^"]+?)":(\S+?)(\s)/);
    }

    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        def text = result.group(1)
        def link = result.group(2)
        def space = result.group(3)


        if (link.startsWith("http://") || link.startsWith("https://")) {
            buffer << "${link}[$text]$space"
        }
        else {
            buffer << "link:$link[$text]$space"
        }
    }
}
