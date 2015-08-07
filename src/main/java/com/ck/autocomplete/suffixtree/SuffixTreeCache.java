package com.ck.autocomplete.suffixtree;

import java.util.Set;

import com.ck.autocomplete.SearchElement;
import com.ck.autocomplete.loader.ISearchElementLoader;

public class SuffixTreeCache<TModel> {

	private final ISearchElementLoader<TModel> loader;
	private SuffixTree<TModel> tree;

	public SuffixTreeCache(ISearchElementLoader<TModel> loader) {
		this.loader = loader;
		refresh();
	}

	public void refresh() {
		Set<SearchElement<TModel>> nodes = loader.load();
		SuffixTree<TModel> tree = new SuffixTree<TModel>();

		for (SearchElement<TModel> node : nodes) {
			tree.addSearchElement(node);
		}
		synchronized (this) {
			this.tree = tree;
		}
	}

	public SuffixTree<TModel> getInstance() {
		return tree;
	}

}
