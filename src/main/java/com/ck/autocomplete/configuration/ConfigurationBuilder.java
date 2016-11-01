package com.ck.autocomplete.configuration;


import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.mapper.CharMapper;
import com.ck.autocomplete.mapper.TurkishCharMapper;

public class ConfigurationBuilder<T> {

    private static final int DEFAULT_MAX_RESULT_COUNT = 30;

    private Tokenizer tokenizer;
    private SearchElementLoader<T> searchElementLoader;
    private int maxResultCount;

    public ConfigurationBuilder(SearchElementLoader<T> searchElementLoader) {
        this.searchElementLoader = searchElementLoader;
        this.tokenizer = new StringTokenizer(new TurkishCharMapper());
        this.maxResultCount = DEFAULT_MAX_RESULT_COUNT;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public ConfigurationBuilder<T> setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        return this;
    }

    public ConfigurationBuilder<T> setCharMapper(CharMapper charMapper) {
        this.tokenizer = new StringTokenizer(charMapper);
        return this;
    }

    public SearchElementLoader<T> getSearchElementLoader() {
        return searchElementLoader;
    }

    public ConfigurationBuilder<T> setSearchElementLoader(SearchElementLoader<T> searchElementLoader) {
        this.searchElementLoader = searchElementLoader;
        return this;
    }

    public int getMaxResultCount() {
        return maxResultCount;
    }

    public ConfigurationBuilder<T> setMaxResultCount(int maxResultCount) {
        this.maxResultCount = maxResultCount;
        return this;
    }

    public Configuration<T> build() {
        return new Configuration<>(tokenizer, searchElementLoader, maxResultCount);
    }

}
