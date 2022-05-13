package com.gareth.filter.trie;

public class TrieNode {

    private final TrieNode[] children;
    private final int offset;
    private boolean isLeafNode;

    /**
     * Construct a new trie node.
     * @param offset initialises the quantity of child nodes to memory consumption.
     */
    public TrieNode(int offset) {
        this.children = new TrieNode[offset];
        this.offset = offset;
        this.isLeafNode = false;
    }

    public TrieNode getChildAt(int index) {
        return children[index];
    }

    public void setChildAt(int index) {
        children[index] = new TrieNode(offset);
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }
}