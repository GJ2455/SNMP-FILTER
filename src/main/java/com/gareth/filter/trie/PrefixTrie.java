package com.gareth.filter.trie;

import java.util.Set;

/**
 * PrefixTrie implementation to provide efficient look up operations on the supplied prefixes.
 * Provides function {@link matchPrefix} which is the main function of this application.
 */
public class PrefixTrie {

    private final TrieNode root;
    private final TrieConfig config;

    public PrefixTrie(TrieConfig config) {
        this.root = new TrieNode(config.getOffset());
        this.config = config;
    }

    public PrefixTrie(TrieConfig config, Set<String> prefixes) {
        this.root = new TrieNode(config.getOffset());
        this.config = config;
        addAll(prefixes);
    }

    public void addAll(Set<String> inserts) {
        inserts.stream().sorted((s1, s2) -> s2.length() - s1.length())
                .forEach(this::insert);
    }

    public void insert(String insert) {
        TrieNode head = root;

        int[] charToIndex = config.convertToSupportedIndexes(insert.toCharArray());

        if (charToIndex == null) {
            throw new IllegalArgumentException(String.format("Failed to insert the entry %s as it contains illegal characters", insert));
        }

        for (int i = 0; i < insert.length(); i++) {
            int charIndex = charToIndex[i];

            if (head.getChildAt(charIndex) == null) {
                head.setChildAt(charIndex);
            }
            head = head.getChildAt(charIndex);
        }

        head.setLeafNode(true);
    }

    /**
     * Matches or partially matches the input to a prefix stored in the trie.
     * <p>
     * A match is considered true if the input is prefixed fully by a configured prefix in the trie
     *
     * @param input string to test
     * @return true if match
     */
    public boolean matchPrefix(String input) {
        TrieNode head = root;

        if (input.length() == 0) {
            return false;
        }

        int[] charToIndex = config.convertToSupportedIndexes(input.toCharArray());

        if (charToIndex == null) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            int charIndex = charToIndex[i];
            head = head.getChildAt(charIndex);

            if (head == null) {
                return false;
            }

            if (head.isLeafNode()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if an input is an exact match in this trie.
     *
     */
    public boolean find(String input) {
        TrieNode head = root;

        if (input.length() == 0) {
            return false;
        }

        int[] charToIndex = config.convertToSupportedIndexes(input.toCharArray());

        if (charToIndex == null) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            int charIndex = charToIndex[i];
            head = head.getChildAt(charIndex);

            if (head == null) {
                return false;
            }
        }
        return head.isLeafNode();
    }
}