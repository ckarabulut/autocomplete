package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.element.SearchElement;

import java.util.Set;

/**
 * Created by caglar on 25-Oct-16.
 */
public class SuffixTreeFactory<T> {
    private SearchElementLoader<T> searchElementLoader;

    public SuffixTreeFactory(SearchElementLoader<T> searchElementLoader) {
        this.searchElementLoader = searchElementLoader;
    }

    public SuffixTree<T> create() {
        SuffixTree<T> newTree = new SuffixTree<>();
        Set<SearchElement<T>> elements = searchElementLoader.load();

        for (SearchElement<T> element : elements) {
            for (String index : element.getIndexSet()) {
                addWord(index, 0, element, newTree.getRoot());
            }
        }
        return newTree;
    }

    private void addWord(String word, int currentIndex, SearchElement<T> element, SuffixTreeNode<T> root) {
        if (word.isEmpty()) {
            return;
        }

        SuffixTreeNode<T> currentNode = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            currentNode.addChild(new SuffixTreeNode<>(chars[i]));
            currentNode = currentNode.getChild(chars[i]);
        }
        currentNode.addSearchElement(currentIndex, element);
        addWord(word.substring(1), currentIndex + 1, element, root);
    }

}
