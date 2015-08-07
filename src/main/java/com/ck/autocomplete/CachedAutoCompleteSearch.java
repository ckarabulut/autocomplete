package com.ck.autocomplete;

import java.util.List;

import com.ck.autocomplete.charmapper.CharConverter;
import com.ck.autocomplete.loader.ISearchElementLoader;
import com.ck.autocomplete.suffixtree.SuffixTreeCache;
import com.ck.autocomplete.suffixtree.SuffixTreeSearch;
import com.ck.autocomplete.utility.StringNormalizer;

public class CachedAutoCompleteSearch<TModel> implements ICachedAutoCompleteSearch<TModel> {

	private SuffixTreeCache<TModel> suffixTreeCache;
	private IAutoCompleteSearch<TModel> autoCompleteSearchBase;

	public CachedAutoCompleteSearch(ISearchElementLoader<TModel> elementLoader) {
		this.suffixTreeCache = new SuffixTreeCache<TModel>(elementLoader);
		SuffixTreeSearch<TModel> treeSearch = new SuffixTreeSearch<TModel>(suffixTreeCache);
		this.autoCompleteSearchBase = new AutoCompleteSearch<TModel>(treeSearch, new StringNormalizer());
	}

	public CachedAutoCompleteSearch(ISearchElementLoader<TModel> elementLoader, CharConverter charConverter) {
		this.suffixTreeCache = new SuffixTreeCache<TModel>(elementLoader);
		SuffixTreeSearch<TModel> treeSearch = new SuffixTreeSearch<TModel>(suffixTreeCache);
		StringNormalizer normalizer = new StringNormalizer(charConverter);
		this.autoCompleteSearchBase = new AutoCompleteSearch<TModel>(treeSearch, normalizer);
	}

	@Override
	public List<SearchElement<TModel>> getSearchResults(String words) {
		return autoCompleteSearchBase.getSearchResults(words);
	}

	@Override
	public void refreshCache() {
		suffixTreeCache.refresh();
	}

}
