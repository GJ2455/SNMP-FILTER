package com.gareth.filter.handler;

import com.gareth.filter.mapping.ConfigurationLoader;
import com.gareth.filter.trie.PrefixTrie;
import java.util.Scanner;
import java.util.logging.Logger;

public class Handler implements Runnable {
    private static final Logger log = Logger.getLogger(String.valueOf(ConfigurationLoader.class));

    private final PrefixTrie trie;
    private final Scanner scanner;

    public Handler(PrefixTrie trie, Scanner in) {
        this.trie = trie;
        this.scanner = in;
    }

    @Override
    public void run() {
        log.info("Ready to perform filtering.");
        while (true) {
            System.out.println("Enter...");

            String input = scanner.nextLine();
            if ("q".equalsIgnoreCase(input)) {
                return;
            }
            showResult(input.trim());
        }

    }
    private String resultFormatter(String input, boolean result) {
        return String.format("%s:%s", input, result);
    }

    private void showResult(String input) {
        System.out.println(resultFormatter(input, this.trie.matchPrefix(input)));
    }
}