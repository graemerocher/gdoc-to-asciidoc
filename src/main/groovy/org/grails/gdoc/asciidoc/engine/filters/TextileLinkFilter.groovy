package org.grails.gdoc.asciidoc.engine.filters

import groovy.transform.CompileStatic
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by ug on 02.06.16.
 */
@CompileStatic
class TextileLinkFilter extends RegexTokenFilter {

    TextileLinkFilter() {

        super(/("([^"]+?)"|\[([^\[]+?)\]):(\S+?)(\s)/)
    }

    @Override
    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {

        String label = result.group(2)
        if ( label == null ) label = result.group(3)
        String link = result.group(4)

        buffer << link + "[$label] "
    }
}
