package com.ck.autocomplete.suffixtree.node;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SuffixTreeInternalNode<TModel> extends SuffixTreeNode<TModel> {

	private Map<Character, SuffixTreeNode<TModel>> children;

	public SuffixTreeInternalNode(char c) {
		super(c);
		this.children = new HashMap<Character, SuffixTreeNode<TModel>>();
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

}
