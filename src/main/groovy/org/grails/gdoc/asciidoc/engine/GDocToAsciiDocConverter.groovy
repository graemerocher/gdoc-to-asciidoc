package org.grails.gdoc.asciidoc.engine

import groovy.io.FileType
import groovy.text.Template
import groovy.text.TemplateEngine
import groovy.transform.CompileStatic
import org.grails.gdoc.asciidoc.engine.filters.HeadingFilter
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
        String srcPath = srcDir.canonicalPath
        String guidePath = new File(srcDir, "guide").canonicalPath
        String refPath = new File(srcDir, "ref").canonicalPath
        boolean hasRef = false
        for(file in gdocFiles) {

            String fileFullPath = file.canonicalPath
            if(fileFullPath.startsWith(guidePath)) {

                String relativePath = fileFullPath - guidePath
                relativePath = relativePath - ".gdoc"
                relativePath += '.adoc'

                writeAsciiDocTemplate(relativePath, file, templateEngine)

            }
            else if(fileFullPath.startsWith(refPath)) {
                hasRef = true
                String relativePath = fileFullPath - srcPath
                relativePath = relativePath - ".gdoc"
                relativePath += '.adoc'
                writeAsciiDocTemplate(relativePath, file, templateEngine, 5)
            }

        }
        if(hasRef) {

            def refDir = new File(srcDir, "ref")
            String newLine = System.getProperty('line.separator')
            if(refDir.exists()) {
                StringBuilder output = new StringBuilder()
                output.append("[[reference]]").append(newLine)
                output.append("== Reference").append(newLine).append(newLine)
                refDir.eachDir { File dir ->
                    def dirName = dir.name
                    def category = dirName.toLowerCase().replace(' ', '-')
                    if(!dir.isHidden() && !dir.name.startsWith(".")) {
                        output.append("[[ref-${category}]]").append(newLine)
                        output.append("=== ${dir.name}").append(newLine).append(newLine)
                        dir.eachFile(FileType.FILES) { File f ->
                            if(f.name.endsWith('.gdoc')) {
                                def refName = f.name - '.gdoc'
                                output.append("[[ref-${category}-${refName}]]").append(newLine)
                                output.append("==== ${refName}").append(newLine).append(newLine)
                                        .append("include::ref/${dirName}/${refName}.adoc[]").append(newLine).append(newLine)

                            }
                        }
                    }
                }

                new File(destDir, "index.adoc").append(output.toString())
            }
        }
    }

    public void writeAsciiDocTemplate(String relativePath, File file, AsciiDocTemplateEngine templateEngine, Integer normalizeLevel = null) {
        def destFile = new File(destDir, relativePath)
        destFile.parentFile.mkdirs()
        file.withReader { BufferedReader reader ->
            def template = templateEngine.createTemplate(reader)
            if(normalizeLevel != null) {
                template.parameters.put(HeadingFilter.NORMALIZE_LEVEL, normalizeLevel)
            }
            destFile.withWriter { BufferedWriter writer ->
                template.make().writeTo(writer)
            }

        }
    }
}
