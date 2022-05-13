package com.gareth.filter.trie;

import java.util.Arrays;

/**
 * Configuration class for {@link SNMPTrie} instance lifecycle.
 *
 * Initializes an int array {@link allowedCharsToIndex} where allowed characters can be looked up with their ascii value and
 * return a configured index value which is inserted/looked up in an array of {@link TrieNode} children.
 *
 * Example :
 *
 * initAllowedChars -> {a,b}
 * char 'a' = 65
 * char 'b' = 66
 *
 * allowedChars[0] = -1 //ascii value not allowed.
 * ...
 * allowedCharsToIndex[65] = 0
 * allowedCharsToIndex[66] = 1
 *
 * Characters which are not allowed will be set to a default value of -1.
 *
 */
public class TrieConfig {

    private final int[] allowedCharsToIndex;
    private final int offset;

    public TrieConfig(char[] allowedCharacters) {
        this.allowedCharsToIndex = initializeAllowedCharactersToIndexes(allowedCharacters);
        this.offset = allowedCharacters.length;
    }

    public int getOffset() {
        return offset;
    }


    private int[] initializeAllowedCharactersToIndexes(char[] supportedCharacters) {
        int maxChar = -1;

        for (char c : supportedCharacters) {
            if (c > maxChar) {
                maxChar = c;
            }
        }

        int[] charToIndex = new int[maxChar + 1];

        //default all indexes to -1 and set a new value at supported character indexes

        Arrays.fill(charToIndex, -1);
        for (int i = 0; i < supportedCharacters.length; i++) {
            charToIndex[supportedCharacters[i]] = i;
        }

        return charToIndex;
    }

    /**
     * Lookup the mapped index for each character supplied and return them as a new int[] array.
     *
     * @param chars the character to convert.
     * @return an array of the indexes each char maps to within the configuration, null if contains an unsupported char.
     */
    public int[] convertToSupportedIndexes(char[] chars) {
        int[] result = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int index = charToIndex(chars[i]);
            if (index == -1) {
                return null;
            } else {
                result[i] = index;
            }
        }

        return result;
    }

    /**
     * Convert the character to the index stored in this configuration.
     *
     * @param c character
     * @return the configured index value
     */
    private int charToIndex(char c) {
        return c > this.allowedCharsToIndex.length ? -1 : allowedCharsToIndex[c];
    }
}