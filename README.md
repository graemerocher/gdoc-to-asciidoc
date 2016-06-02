Grails GDoc to AsciiDoc Converter
=================================

This plugin will convert Grails' GDoc format to AsciiDoc.

To use the plugin add it to your `build.gradle` build classpath dependencies:

```groovy
buildscript {
    repositories {
        maven { url "https://repo.grails.org/grails/core" }
    }
    dependencies {
        classpath "org.grails:gdoc-to-asciidoc:1.0.1"
    }
}
```

Then apply the plugin:

```groovy
apply plugin: "org.grails.grails-gdoc-to-asciidoc"
```

It is recommended that you specify the packages that your plugin provides. For example:

```groovy
gdoc2asciidoc {
    apiLinks = ['grails.plugin.cache':"link:api/"]
}
```

Then run `gradle gdoc2asciidoc`:

```bash
$ gradle gdoc2asciidoc
```

The Asciidoc content will be generated to `src/docs/asciidoc` by default.