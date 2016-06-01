package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by ug on 01.06.16.
 */
class HeadingFilter extends RegexTokenFilter {

    HeadingFilter() {

        super(/^h(\d{1})\.\s+(.+)$/)
    }

    @Override
    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {

        Integer level = result.group(1)
        String headerText = result.group(2)

        buffer << '=' * level + ' ' + headerText
    }
}
