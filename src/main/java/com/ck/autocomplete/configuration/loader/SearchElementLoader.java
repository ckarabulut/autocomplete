package com.ck.autocomplete.configuration.loader;

import com.ck.autocomplete.element.SearchElement;

import java.util.Set;

public interface SearchElementLoader<TModel> {

    Set<SearchElement<TModel>> load();

}
