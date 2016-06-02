package org.grails.gdoc.asciidoc.engine.filters

import groovy.transform.CompileStatic
import org.radeox.filter.CacheFilter
import org.radeox.filter.context.FilterContext
import org.radeox.filter.regex.RegexTokenFilter
import org.radeox.regex.MatchResult

/**
 * Created by ug on 01.06.16.
 */
@CompileStatic
class HeadingFilter extends RegexTokenFilter implements CacheFilter {


    public static final String NORMALIZE_LEVEL = "normalizeLevel"

    HeadingFilter() {
        super(/(?m)^h(\d)\.\s+?(.*?)$/)
    }

    @Override
    void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {

        String level = result.group(1)
        String headerText = result.group(2)
        def normalizedLevel = context.getRenderContext().getParameters().get(NORMALIZE_LEVEL)
        if(normalizedLevel) {
            level = normalizedLevel
        }
        def levelNumber = level.toInteger()
        String newHeaderText = '=' * levelNumber + ' ' + headerText
        buffer << System.getProperty("line.separator") << newHeaderText << System.getProperty("line.separator")
    }
}
