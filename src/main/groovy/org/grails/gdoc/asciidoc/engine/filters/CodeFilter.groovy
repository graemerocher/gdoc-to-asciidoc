package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by graemerocher on 01/06/2016.
 */
class CodeFilter extends RegexTokenFilter {
    CodeFilter() {
        super(/@([^\n]*?)@/);
    }

    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        def text = result.group(1)
        // are we inside a code block?
        if (text.indexOf('class="code"') > -1) {
            buffer << "@$text@"
        }
        else {
            buffer << "`${text}`"
        }
    }
}
