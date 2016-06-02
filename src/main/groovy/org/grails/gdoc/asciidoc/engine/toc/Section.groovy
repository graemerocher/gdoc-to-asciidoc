package org.grails.gdoc.asciidoc.engine.toc

import groovy.transform.ToString

@ToString
class Section {
    int level
    String id
    String filename
    String title
}
