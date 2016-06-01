package org.grails.gdoc.asciidoc.engine.toc

import groovy.transform.ToString

@ToString
class Section {
    int level
    String filename
    String title
}
