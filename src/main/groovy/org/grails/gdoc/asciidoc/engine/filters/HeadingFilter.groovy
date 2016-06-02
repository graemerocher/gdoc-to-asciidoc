package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.filter.CacheFilter
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by ug on 01.06.16.
 */
class HeadingFilter extends RegexTokenFilter implements CacheFilter {

    HeadingFilter() {
        super(/(?m)^h(\d)\.\s+?(.*?)$/)
    }

    @Override
    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {

        String level = result.group(1)
        String headerText = result.group(2)

        String newHeaderText = '=' * level.toInteger() + ' ' + headerText
        buffer << newHeaderText
    }
}
