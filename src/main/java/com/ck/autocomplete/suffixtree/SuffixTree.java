package com.ck.autocomplete.suffixtree;

public class SuffixTree<TModel> {

    private final SuffixTreeNode<TModel> root;

    public SuffixTree() {
        root = new SuffixTreeNode<>('$');
    }

    public SuffixTreeNode<TModel> getRoot() {
        return root;
    }


}
