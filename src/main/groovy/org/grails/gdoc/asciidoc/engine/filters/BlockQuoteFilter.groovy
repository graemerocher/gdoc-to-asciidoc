package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

class BlockQuoteFilter extends RegexTokenFilter {
    BlockQuoteFilter() {
        super(/(?m)^bc.\s*?(.*?)\n\n/);
    }

    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        buffer << "----\n${result.group(1)}\n----\n"
    }
}
