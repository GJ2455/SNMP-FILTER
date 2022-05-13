package com.gareth.filter.handler;

import com.gareth.filter.mapping.ConfigurationLoader;
import com.gareth.filter.trie.SNMPTrie;

import java.util.Scanner;
import java.util.logging.Logger;

public class Handler implements Runnable {

    private static final Logger log = Logger.getLogger(String.valueOf(ConfigurationLoader.class));

    private final SNMPTrie trie;
    private final Scanner scanner;

    public Handler(SNMPTrie trie, Scanner in) {
        this.trie = trie;
        this.scanner = in;
    }

    @Override
    public void run() {
        log.info("Ready to perform filtering.");
        while (true) {
            System.out.println("Enter...");

            String oid = scanner.nextLine();
            if ("q".equalsIgnoreCase(oid)) {
                return;
            }
            showResult(oid.trim());
        }

    }
    private String resultFormatter(String oid, boolean result) {
        return String.format("%s:%s", oid, result);
    }

    private void showResult(String oid) {
        System.out.println(resultFormatter(oid, this.trie.matchPrefix(oid)));
    }
}