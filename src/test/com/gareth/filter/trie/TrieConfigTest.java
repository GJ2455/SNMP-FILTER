package com.gareth.filter.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrieConfigTest {

    @Test
    public void testConfigurationConvertToSupportedIndexesReturnsNullWhenNotSupportedCharsSupplied() {
        char[] supportedCharacters = new char[] {'0','1','2','3','4','5','6','7','8','9','.'};
        char[] unsupportedChars = new char[] {'a','b','c','d'};

        TrieConfig trieConfig = new TrieConfig(supportedCharacters);

        int[] converted = trieConfig.convertToSupportedIndexes(unsupportedChars);

        assertNull(converted);
    }

    @Test
    public void testConfigurationConvertToSupportedIndexesWhenSupportedCharsSupplied() {
        char[] supportedCharacters = new char[] {'0','1','2','3','4','5','6','7','8','9','.'};
        TrieConfig trieConfig = new TrieConfig(supportedCharacters);
        int[] converted = trieConfig.convertToSupportedIndexes(supportedCharacters);

        assertEquals(11, converted.length);
    }


    @Test
    public void correctlyMapsCharsToConfiguredIndexValue() {
        char[] supportedCharacters = new char[] {'Z','1','2'};

        TrieConfig trieConfig = new TrieConfig(supportedCharacters);

        int[] result = trieConfig.convertToSupportedIndexes(supportedCharacters);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
        assertEquals(2, result[2]);
    }
}