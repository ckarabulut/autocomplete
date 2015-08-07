package com.ck.autocomplete.suffixtree.node;

public class SuffixTreeNode<TModel> {

	private char value;

	public SuffixTreeNode(char c) {
		this.value = c;
	}

	public char getChar() {
		return value;
	}
	
}
