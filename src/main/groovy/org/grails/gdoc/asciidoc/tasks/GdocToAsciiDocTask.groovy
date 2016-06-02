package org.grails.gdoc.asciidoc.tasks

import groovy.transform.CompileStatic
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.Input
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

    @Input
    Map<String, String> apiLinks = [:]

    @TaskAction
    void run() {

        def allFiles = project.fileTree(srcDir)
                                .filter { File f -> f.name.endsWith('.gdoc') }
                                .files


        GDocToAsciiDocConverter converter = new GDocToAsciiDocConverter(project.version.toString(), apiLinks)

        converter.setSrcDir(srcDir)
        if(resourcesDir == null) {
            resourcesDir = srcDir
        }
        converter.setResourcesDir(resourcesDir)
        converter.setDestDir(destDir)
        converter.setGdocFiles(allFiles)
        converter.execute()

        def imagesDir = new File(resourcesDir, "img")
        if(imagesDir.exists()) {

            project.copy(new Action<CopySpec>() {
                @Override
                void execute(CopySpec copySpec) {

                    copySpec.from(imagesDir)
                            .into(new File(destDir, "images"))
                }
            })
        }

        println "GDocs successfully converted to ${destDir}. Now add the following to build.gradle"
        println """
plugins {
    id 'org.asciidoctor.convert' version '1.5.3'
}

asciidoctor {
    resources {
        from('src/docs/images')
        into "./images"
    }

    attributes 'experimental'  : 'true',
               'compat-mode'   : 'true',
               'toc'           : 'left',
               'icons'         : 'font',
               'version'       : project.version,
               'sourcedir'     : 'src/main/groovy'
}

task apiDocs(type: Copy) {
    from groovydoc.outputs.files
    into file("\${buildDir}/asciidoc/html5/api")
}

asciidoctor.dependsOn(apiDocs)

"""
    }
}
