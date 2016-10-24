package com.ck.autocomplete.configuration;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;

public class Configuration<T> {

    private final Tokenizer tokenizer;
    private final SearchElementLoader<T> searchElementLoader;


    public Configuration(Tokenizer tokenizer, SearchElementLoader<T> searchElementLoader) {
        this.tokenizer = tokenizer;
        this.searchElementLoader = searchElementLoader;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public SearchElementLoader<T> getSearchElementLoader() {
        return searchElementLoader;
    }
}
