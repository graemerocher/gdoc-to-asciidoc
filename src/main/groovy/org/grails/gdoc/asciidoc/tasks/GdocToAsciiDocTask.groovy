package org.grails.gdoc.asciidoc.tasks

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.grails.gdoc.asciidoc.engine.toc.TocProcessor

/**
 * Takes a source directory containing GDoc and outputs AsciiDoc to the target directory
 */
@CompileStatic
class GdocToAsciiDocTask extends DefaultTask {

    @InputDirectory
    File srcDir

    @InputDirectory
    File resourcesDir

    @OutputDirectory
    File destDir

    @TaskAction
    void run() {
        TocProcessor parser = new TocProcessor(version: project.version.toString(), destDir: destDir, resourcesDir: resourcesDir, srcDir: srcDir)
        parser.parse()

        //TODO: call the other guys task
    }
}
