package com.gareth.filter.trie;

import com.gareth.filter.trie.PrefixTrie;
import com.gareth.filter.trie.TrieConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PrefixTrieTest {

    private PrefixTrie createTrie() {
        TrieConfig trieConfig = new TrieConfig(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'});
        return new PrefixTrie(trieConfig);
    }

    @ParameterizedTest
    @ValueSource(strings =
            {".1.3.6.1.4.1.9.9.117",
                    "0.1.2.3.4.5.6",
                    "...........",
                    ".000000000",
                    "9876543.."
            })
    public void testInsertSucceedsWhenValidCharacter(String value) {
        PrefixTrie trie = createTrie();
        trie.insert(value);
        assertTrue(trie.find(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {".1.3.6.1.4.1.9.9.z", "x", ",,,", ".1.2-"})
    public void testInputFailsWhenIllegalCharacterSupplied(String value) {
        TrieConfig trieConfig = new TrieConfig(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'});
        PrefixTrie trie = new PrefixTrie(trieConfig);
        assertThrows(IllegalArgumentException.class, () -> {
            trie.insert(value);
        });
    }

    @ParameterizedTest
    @ValueSource(strings =
    {".2.4.5.6.7.8.9.0.134.1.2.7",
    ".2.4.5.6.7.8.9.0.134.9",
    ".2.4.5.6.7.8.9.0.132",
    ".2.4.5.6.7.8.9.1.0",
    ".2.4.5.6.7.8.9.3",
    ".2.4.5.6.7.8.1",
    ".2.4.5.6.7.4",
    ".2.3.4",
    ".1"
    })
    public void verifyMatchPrefixReturnsFalseWhenNoPartialMatchFound(String value) {
        PrefixTrie trie = createTrie();
        trie.insert(".2.4.5.6.7.8.9.0.134.1.2.3");

        Assertions.assertFalse(trie.matchPrefix(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {".1.3.6.1.4.1.9.9.117.123.4.5", ".1.3.6.1.4.1.9.9.117.0", ".1.3.6.1.4.1.9.9.117.1.1.1.1"})
    public void verifyMatchPrefixReturnsTrueIfInputFullyPrefixedByEntryInTrie(String value) {
        PrefixTrie trie = createTrie();
        trie.insert(".1.3.6.1.4.1.9.9.117");

        Assertions.assertTrue(trie.matchPrefix(value));
    }
}