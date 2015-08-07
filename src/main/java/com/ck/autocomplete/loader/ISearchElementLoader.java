package com.ck.autocomplete.loader;

import java.util.Set;

import com.ck.autocomplete.SearchElement;

public interface ISearchElementLoader<TModel> {

	public Set<SearchElement<TModel>> load();

}
