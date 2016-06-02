package org.grails.gdoc.asciidoc

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.grails.gdoc.asciidoc.tasks.GdocToAsciiDocTask

/**
 * Created by graemerocher on 01/06/2016.
 */
@CompileStatic
class GdocToAsciiDocConverterPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        def gdocToAsciiDocTask = project.tasks.create('gdoc2asciidoc', GdocToAsciiDocTask)

        gdocToAsciiDocTask.srcDir = project.file("src/docs")
        gdocToAsciiDocTask.destDir = project.file("src/docs/asciidoc")
    }
}
