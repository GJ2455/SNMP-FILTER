package com.gareth.filter.mapping;

import com.gareth.filter.mapping.ConfigurationLoader;
import com.gareth.filter.mapping.PrefixConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest {

    @Test
    public void configurationLoadsAllPrefixes() {

        String path = "src/test/resources/test.yaml";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        PrefixConfig configuredPrefixes = ConfigurationLoader.loadFromConfigFile(absolutePath);
        assertEquals(8, configuredPrefixes.getPrefixes().size());

        List<String> expected = new ArrayList<>();
        expected.add(".1.3.6.1.6.3.1.1.5");
        expected.add(".1.3.6.1.2.1.10.166.3");
        expected.add(".1.3.6.1.4.1.9.9.117.2");
        expected.add(".1.3.6.1.2.1.10.32.0.1");
        expected.add(".1.3.6.1.2.1.14.16.2.2");
        expected.add(".1.3.6.1.4.1.9.10.137.0.1");
        expected.add(".1.3.6.1.6.3.1.1.6");
        expected.add(".1.3.6.1.2.1.10.166.4");

        List<String> actual = new ArrayList<>(configuredPrefixes.getPrefixes());

        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void throwsWhenWrongKeyNameSupplied() {
        String badFile = "src/test/resources/wrong-key-name.yaml";

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            ConfigurationLoader.loadFromConfigFile(badFile);
        });

        Assertions.assertEquals("No prefixes where found in the file. Check the property key is correct.", thrown.getMessage());
    }

    @Test
    public void throwsWhenWrongFileFormatSupplied() {
        String badFile = "src/test/resources/test.txt";

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            ConfigurationLoader.loadFromConfigFile(badFile);
        });

        Assertions.assertEquals("Expected file extension is .yaml", thrown.getMessage());
    }
}