package org.grails.gdoc.asciidoc.engine

import groovy.text.Template
import groovy.text.TemplateEngine
import groovy.transform.CompileStatic
import org.grails.gdoc.asciidoc.engine.toc.TocProcessor

/**
 * Created by graemerocher on 02/06/2016.
 */
@CompileStatic
class GDocToAsciiDocConverter {

    File destDir = new File("src/docs/asciidoc")
    File srcDir = new File("src/docs")
    File resourcesDir = srcDir
    Collection<File> gdocFiles = []

    final String version
    final Map<String, String> apiLinks

    GDocToAsciiDocConverter(String version, Map<String, String> apiLinks = [:]) {
        this.version = version
        this.apiLinks = apiLinks
    }

    void execute() {
        destDir.mkdirs()
        TocProcessor tocProcessor = new TocProcessor(version: version, resourcesDir: resourcesDir, srcDir: srcDir, destDir: destDir)
        tocProcessor.parse()
        TemplateEngine templateEngine = new AsciiDocTemplateEngine(apiLinks)
        String guidePath = new File(srcDir, "guide").canonicalPath

        for(file in gdocFiles) {

            String fileFullPath = file.canonicalPath
            if(fileFullPath.startsWith(guidePath)) {

                String relativePath = fileFullPath - guidePath
                relativePath = relativePath - ".gdoc"
                relativePath += '.adoc'

                def destFile = new File(destDir, relativePath)
                destFile.parentFile.mkdirs()
                file.withReader { BufferedReader reader ->
                    def template = templateEngine.createTemplate(reader)
                    destFile.withWriter { BufferedWriter writer ->
                        template.make().writeTo(writer)
                    }

                }

            }

        }
    }
}
