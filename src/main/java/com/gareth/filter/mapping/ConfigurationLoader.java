package com.gareth.filter.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Utility class for loading the prefix configuration.
 */
public final class ConfigurationLoader {

    private static Logger log = Logger.getLogger(String.valueOf(ConfigurationLoader.class));
    private static final Pattern FILE_EXTENSION = Pattern.compile("^.*\\.yaml$");

    private ConfigurationLoader() {}

    public static TrapTypeOidPrefixes loadFromConfigFile(String configLocation) {
        validateFileFormat(configLocation);

        File config = new File(configLocation);
        TrapTypeOidPrefixes prefixes;
        try {
            prefixes = parse(config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the configuration file.", e);
        }

        if (prefixes.getTrapTypeOidPrefix() == null) {
            throw new RuntimeException("No prefixes where found in the file. Check the property key is correct.");
        }

        log.info(String.format("Successfully parsed the configuration file [%s]", configLocation));
        return prefixes;
    }

    private static TrapTypeOidPrefixes parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(file, TrapTypeOidPrefixes.class);
    }

    private static void validateFileFormat(String fileName) {
        if (!FILE_EXTENSION.matcher(fileName).matches()) {
            throw new RuntimeException("Expected file extension is .yaml");
        }
    }
}