package com.gareth.filter.props;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public final class AppProps {

    private final static URL PROPS_URL = ClassLoader.getSystemResource("app.properties");
    public static final String ALLOWED_PREFIXES_PROP = "trie.allowedChars";

    private AppProps() {}

    public static char[] getAllowedChars() throws IOException {
        return getProperty(ALLOWED_PREFIXES_PROP).toCharArray();
    }

    private static String getProperty(String value) throws IOException {
        Properties properties = new Properties();
        properties.load(PROPS_URL.openStream());
        return properties.getProperty(value);
    }
}