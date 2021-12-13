package fr.pomie.working_mate.util;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;

public abstract class RessourceAccessor {

    public static final String RES = "/home/yann/Documents/sources/working_mate/src/main/resources/";
    public static final String PACKAGE = "fr/pomie/working_mate/";

    static public InputStream getStream(String path) {
        ClassLoader cl = RessourceAccessor.class.getClassLoader();
        return cl.getResourceAsStream(path);
    }
}
