package org.grails.gdoc.asciidoc.engine

import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import groovy.text.TemplateEngine
import groovy.transform.CompileStatic
import org.codehaus.groovy.control.CompilationFailedException
import org.radeox.api.engine.RenderEngine
import org.radeox.api.engine.context.RenderContext
import org.radeox.engine.BaseRenderEngine
import org.radeox.engine.context.BaseRenderContext

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class AsciiDocTemplateEngine extends TemplateEngine {
    @Override
    Template createTemplate(Reader reader) throws CompilationFailedException, ClassNotFoundException, IOException {
        RenderContext context = new BaseRenderContext()
        RenderEngine engine = new AsciiDocEngine()
        String renderedText = engine.render(reader , context)

        TemplateEngine templateEngine = new SimpleTemplateEngine();
        return templateEngine.createTemplate(new StringReader(renderedText))
    }
}
