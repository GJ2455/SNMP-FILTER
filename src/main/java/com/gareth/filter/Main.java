package com.gareth.filter;

import com.gareth.filter.mapping.ConfigurationLoader;
import com.gareth.filter.handler.Handler;
import com.gareth.filter.props.AppProps;
import com.gareth.filter.trie.PrefixTrie;
import com.gareth.filter.trie.TrieConfig;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String configFile = Optional.ofNullable(System.getenv("TRIE_PREFIXES")).orElseThrow(
                () -> new RuntimeException("TRIE_PREFIXES is not set in the environment, refer to README.md"));


        PrefixTrie trie = new PrefixTrie(new TrieConfig(AppProps.getAllowedChars()),
                ConfigurationLoader.loadFromConfigFile(configFile).getPrefixes());

        Handler handler = new Handler(trie, new Scanner(System.in));

        handler.run();
    }
}