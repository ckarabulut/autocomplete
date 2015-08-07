package com.ck.autocomplete;

public interface ICachedAutoCompleteSearch<TModel> extends IAutoCompleteSearch<TModel> {

	void refreshCache();

}
