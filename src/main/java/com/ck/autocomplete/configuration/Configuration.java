package com.ck.autocomplete.configuration;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;

public class Configuration<T> {

    private final Tokenizer tokenizer;
    private final SearchElementLoader<T> searchElementLoader;
    private final int defaultMaxSearchResult;

    public Configuration(Tokenizer tokenizer, SearchElementLoader<T> searchElementLoader, int defaultMaxSearchResult) {
        this.tokenizer = tokenizer;
        this.searchElementLoader = searchElementLoader;
        this.defaultMaxSearchResult = defaultMaxSearchResult;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public SearchElementLoader<T> getSearchElementLoader() {
        return searchElementLoader;
    }

    public int getDefaultMaxSearchResult() {
        return defaultMaxSearchResult;
    }
}
