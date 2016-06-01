package org.grails.gdoc.asciidoc.engine.filters

import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by stigmelling on 01.06.16.
 */
class ImageFilter extends RegexTokenFilter {
    ImageFilter() {
        super(/!([^\n]*?)!/);
    }

    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        def text = result.group(1)
        buffer << "image::${text}[]"
    }
}
