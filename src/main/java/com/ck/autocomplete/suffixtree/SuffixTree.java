package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.element.SearchElement;

public class SuffixTree<TModel> {

    private final SuffixTreeNode<TModel> root;

    public SuffixTree() {
        root = new SuffixTreeNode<>('$');
    }

    public SuffixTreeNode<TModel> getRoot() {
        return root;
    }

    public void addSearchElement(SearchElement<TModel> element) {
        for (String word : element.getIndexSet()) {
            addWord(word, 0, element);
        }
    }

    private void addWord(String word, int currentIndex, SearchElement<TModel> element) {
        if (word.isEmpty()) {
            return;
        }

        SuffixTreeNode<TModel> currentNode = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            currentNode.addChild(new SuffixTreeNode<>(chars[i]));
            currentNode = currentNode.getChild(chars[i]);
        }
        currentNode.addSearchElement(currentIndex, element);
        addWord(word.substring(1), currentIndex + 1, element);
    }

}
