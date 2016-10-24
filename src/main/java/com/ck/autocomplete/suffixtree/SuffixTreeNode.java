package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.element.SearchElement;

import java.util.*;

public class SuffixTreeNode<TModel> {

    private char value;
    private Map<Character, SuffixTreeNode<TModel>> children;
    private Map<Integer, Set<SearchElement<TModel>>> valueMap;

    public SuffixTreeNode(char c) {
        this.value = c;
        this.children = new HashMap<>();
        this.valueMap = new HashMap<>();
    }

    public char getChar() {
        return value;
    }

    public void addChild(SuffixTreeNode<TModel> child) {
        if (!children.containsKey(child.getChar())) {
            children.put(child.getChar(), child);
        }
    }

    public SuffixTreeNode<TModel> getChild(char c) {
        return children.get(c);
    }

    public Collection<SuffixTreeNode<TModel>> getChilds() {
        return children.values();
    }

    public void addSearchElement(int index, SearchElement<TModel> value) {
        if (!valueMap.containsKey(index)) {
            valueMap.put(index, new HashSet<>());
        }
        valueMap.get(index).add(value);
    }

    public Map<Integer, Set<SearchElement<TModel>>> getValueMap() {
        return valueMap;
    }
}
