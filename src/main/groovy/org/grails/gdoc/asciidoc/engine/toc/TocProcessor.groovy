package org.grails.gdoc.asciidoc.engine.toc

import groovy.transform.ToString
import org.yaml.snakeyaml.Yaml

class TocProcessor {

    String version
    File resourcesDir
    File srcDir
    File destDir

    void parse() {
        Yaml yaml = new Yaml()

        def toc = yaml.load(new File("${srcDir.absolutePath}/src/en/guide/toc.yml").text)
        int currentLevel = 1
        List<Section> sections = []

        toc.each { key, value ->
            processSection(sections, currentLevel, key, value)
        }

        generateOutput(sections)
    }

    void generateOutput(List<Section> sections) {
        String newLine = System.getProperty('line.separator')
        StringBuffer output = new StringBuffer()
        Properties properties = new Properties()
        new File("${resourcesDir.absolutePath}/doc.prperties").withInputStream {
            properties.load(it)
        }

        output.append("= ${properties.getProperty('title')}").append(newLine)
        output.append(properties.getProperty('authors'))
        output.append(":version: ${version}").append(newLine).append(newLine)
        sections.each { Section section ->
            output.append("="*(section.level+1)).append(" ${section.title}").append(newLine).append(newLine)
                    .append("include::${section.filename}.adoc[]").append(newLine).append(newLine)
        }

        new File("${destDir.absolutePath}/index.adoc").write(output.toString())
    }

    void processSection(List<Section> sections, int currentLevel, String key, def value) {
        if (value instanceof String) {
            sections << new Section(level: currentLevel, filename: key, title: value)
        } else {
            Section section = new Section(level: currentLevel, filename: key, title: value.title)
            sections << section
            value.findAll{k,v -> k != "title"}.each {k,v ->
                processSection(sections, currentLevel+1, k, v)
            }
        }

    }

}

