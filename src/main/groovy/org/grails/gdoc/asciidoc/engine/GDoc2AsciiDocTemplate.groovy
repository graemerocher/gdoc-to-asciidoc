package org.grails.gdoc.asciidoc.engine

import groovy.text.Template
import groovy.transform.CompileStatic
import org.radeox.api.engine.RenderEngine
import org.radeox.api.engine.context.RenderContext
import org.radeox.engine.context.BaseRenderContext

/**
 * Created by graemerocher on 02/06/2016.
 */
@CompileStatic
class GDoc2AsciiDocTemplate implements Template {

    final RenderEngine engine
    final Reader source
    final Map parameters = [:]

    GDoc2AsciiDocTemplate(RenderEngine engine, Reader source) {
        this.engine = engine
        this.source = source
    }

    @Override
    Writable make(Map binding = [:]) {
        RenderContext context = new BaseRenderContext()
        context.setParameters(parameters)
        return new Writable() {

            @Override
            Writer writeTo(Writer writer) throws IOException {
                engine.render(writer, source.text, context)
                return writer
            }
        }
    }
}
