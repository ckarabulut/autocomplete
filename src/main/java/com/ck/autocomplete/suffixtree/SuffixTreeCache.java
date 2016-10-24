package com.ck.autocomplete.suffixtree;

import java.util.Set;

import com.ck.autocomplete.element.SearchElement;
import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.utility.Cache;

public class SuffixTreeCache<TModel> implements Cache<SuffixTree<TModel>>{

	private final SearchElementLoader<TModel> loader;
	private SuffixTree<TModel> tree;

	public SuffixTreeCache(SearchElementLoader<TModel> loader) {
		this.loader = loader;
		refresh();
	}

	@Override
	public void refresh() {
		Set<SearchElement<TModel>> nodes = loader.load();
		SuffixTree<TModel> tree = new SuffixTree<>();

		for (SearchElement<TModel> node : nodes) {
			tree.addSearchElement(node);
		}
		synchronized (this) {
			this.tree = tree;
		}
	}

	@Override
	public SuffixTree<TModel> getInstance() {
		return tree;
	}

}
