package org.grails.gdoc.asciidoc.engine.filters

import groovy.transform.CompileStatic
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by graemerocher on 02/06/2016.
 */
@CompileStatic
class ImageFilter extends RegexTokenFilter {
    ImageFilter() {
        super(/!([^\n<>=]*?\.(jpg|png|gif))!/);
    }
    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
        def text = result.group(1)
        buffer << "image::${text}[]"
    }
}
