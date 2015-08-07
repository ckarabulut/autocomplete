package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.SearchElement;
import com.ck.autocomplete.suffixtree.node.SuffixTreeInternalNode;
import com.ck.autocomplete.suffixtree.node.SuffixTreeLeafNode;

public class SuffixTree<TModel> {

	private final SuffixTreeInternalNode<TModel> root;

	public SuffixTree() {
		root = new SuffixTreeInternalNode<TModel>('$');
	}

	public SuffixTreeInternalNode<TModel> getRoot() {
		return root;
	}

	/**
	 * Add Search Element to Suffix Tree
	 * 
	 * @param element
	 */
	public void addSearchElement(SearchElement<TModel> element) {
		for (String word : element.getSearchIndexSet()) {
			addWord(word, 0, element);
		}
	}

	private void addWord(String word, int currentIndex, SearchElement<TModel> element) {
		if (word.isEmpty()) {
			return;
		}

		SuffixTreeInternalNode<TModel> currentNode = root;
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			currentNode.addChild(new SuffixTreeInternalNode<TModel>(chars[i]));
			currentNode = (SuffixTreeInternalNode<TModel>) currentNode.getChild(chars[i]);
		}

		if (currentNode.getChild('$') == null) {
			currentNode.addChild(new SuffixTreeLeafNode<TModel>());
		}
		SuffixTreeLeafNode<TModel> endNode = (SuffixTreeLeafNode<TModel>) currentNode.getChild('$');
		endNode.addSearchElement(currentIndex, element);

		currentIndex++;
		addWord(word.substring(1), currentIndex, element);
	}

}
