package org.grails.gdoc.asciidoc.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by graemerocher on 02/06/2016.
 */
public class NameUtils {
    /**
     * Retrieves the script name representation of the given class name.
     * For example MyFunkyGrailsScript would be my-funky-grails-script.
     *
     * @param name The class name to convert.
     * @return The script name representation.
     */
    public static String getHyphenSeperatedName(String name) {
        if (name == null) {
            return null;
        }

        if (name.endsWith(".groovy")) {
            name = name.substring(0, name.length()-7);
        }
        return getNaturalName(name).replaceAll("\\s", "-").toLowerCase();
    }

    /**
     * Returns the class name without the package prefix.
     *
     * @param className The class name to get a short name for
     * @return The short name of the class
     */
    public static String getShortName(String className) {
        int i = className.lastIndexOf(".");
        if (i > -1) {
            className = className.substring(i + 1, className.length());
        }
        return className;
    }
    /**
     * Converts a property name into its natural language equivalent eg ('firstName' becomes 'First Name')
     * @param name The property name to convert
     * @return The converted property name
     */
    public static String getNaturalName(String name) {
        name = getShortName(name);
        List<String> words = new ArrayList<String>();
        int i = 0;
        char[] chars = name.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            char c = chars[j];
            String w;
            if (i >= words.size()) {
                w = "";
                words.add(i, w);
            }
            else {
                w = words.get(i);
            }

            if (Character.isLowerCase(c) || Character.isDigit(c)) {
                if (Character.isLowerCase(c) && w.length() == 0) {
                    c = Character.toUpperCase(c);
                }
                else if (w.length() > 1 && Character.isUpperCase(w.charAt(w.length() - 1))) {
                    w = "";
                    words.add(++i,w);
                }

                words.set(i, w + c);
            }
            else if (Character.isUpperCase(c)) {
                if ((i == 0 && w.length() == 0) || (Character.isUpperCase(w.charAt(w.length() - 1)) && Character.isUpperCase(chars[j-1]))) {
                    words.set(i, w + c);
                }
                else {
                    words.add(++i, String.valueOf(c));
                }
            }
        }

        StringBuilder buf = new StringBuilder();
        for (Iterator<String> j = words.iterator(); j.hasNext();) {
            String word = j.next();
            buf.append(word);
            if (j.hasNext()) {
                buf.append(' ');
            }
        }
        return buf.toString();
    }

}
