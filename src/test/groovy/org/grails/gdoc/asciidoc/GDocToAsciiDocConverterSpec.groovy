package org.grails.gdoc.asciidoc

import groovy.io.FileType
import org.grails.gdoc.asciidoc.engine.GDocToAsciiDocConverter
import spock.lang.Specification

/**
 * Created by graemerocher on 02/06/2016.
 */
class GDocToAsciiDocConverterSpec extends Specification {

    void "Test convert gdocs to asciidoc"() {
        given:"a converter"
        GDocToAsciiDocConverter converter = new GDocToAsciiDocConverter("1.0.0")

        def srcDir = new File('src/test/resources')
        def outputDir = new File("build/test-output")

        converter.setSrcDir(srcDir)
        converter.setDestDir(outputDir)
        converter.setResourcesDir(srcDir)
        Collection<File> files = []
        srcDir.eachFileRecurse(FileType.FILES) { File f ->
            if(f.name.endsWith('gdoc')) {
                files.add(f)
            }
        }
        converter.setGdocFiles(files)

        when:"The converter runs"
        converter.execute()

        then:"The result is correct"
        new File(outputDir, "index.adoc").exists()
        new File(outputDir, "usage/dsl.adoc").exists()
    }
}
