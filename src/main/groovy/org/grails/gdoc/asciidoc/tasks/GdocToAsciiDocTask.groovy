package org.grails.gdoc.asciidoc.tasks

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.grails.gdoc.asciidoc.engine.GDocToAsciiDocConverter
import org.grails.gdoc.asciidoc.engine.toc.TocProcessor

/**
 * Takes a source directory containing GDoc and outputs AsciiDoc to the target directory
 */
@CompileStatic
class GdocToAsciiDocTask extends DefaultTask {

    @InputDirectory
    File srcDir

    @InputDirectory
    @Optional
    File resourcesDir

    @OutputDirectory
    File destDir

    @TaskAction
    void run() {

        def allFiles = project.fileTree(srcDir)
                                .filter { File f -> f.name.endsWith('.gdoc') }
                                .files


        GDocToAsciiDocConverter converter = new GDocToAsciiDocConverter(project.version.toString())

        converter.setSrcDir(srcDir)
        if(resourcesDir == null) {
            resourcesDir = srcDir
        }
        converter.setResourcesDir(resourcesDir)
        converter.setDestDir(destDir)
        converter.setGdocFiles(allFiles)
    }
}
