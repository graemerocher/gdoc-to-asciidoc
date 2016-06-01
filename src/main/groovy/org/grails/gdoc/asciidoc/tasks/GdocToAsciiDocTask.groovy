package org.grails.gdoc.asciidoc.tasks

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Takes a source directory containing GDoc and outputs AsciiDoc to the target directory
 */
@CompileStatic
class GdocToAsciiDocTask extends DefaultTask {

    @InputDirectory
    File srcDir

    @OutputDirectory
    File destDir

    @TaskAction
    void run() {
        // TODO
    }
}
