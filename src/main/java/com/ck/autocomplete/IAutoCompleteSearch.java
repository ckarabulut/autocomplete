package com.ck.autocomplete;

import java.util.List;

public interface IAutoCompleteSearch<TModel> {

	List<SearchElement<TModel>> getSearchResults(String words);

}
