package com.ck.autocomplete;

import com.ck.autocomplete.element.SearchElement;

import java.util.List;

public interface Search<TModel> {

	List<SearchElement<TModel>> search(String words);

}
