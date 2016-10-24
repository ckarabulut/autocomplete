package com.ck.autocomplete;

public interface ReloadableSearch<TModel> extends Search<TModel> {

	void reloadModels();

}
