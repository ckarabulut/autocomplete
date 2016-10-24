package com.ck.autocomplete.configuration;


import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.mapper.CharMapper;
import com.ck.autocomplete.mapper.TurkishCharMapper;

public class ConfigurationBuilder<T> {

    private Tokenizer tokenizer;
    private SearchElementLoader<T> searchElementLoader;

    public ConfigurationBuilder(SearchElementLoader<T> searchElementLoader) {
        this.searchElementLoader = searchElementLoader;
        this.tokenizer = new StringTokenizer(new TurkishCharMapper());
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

    public Configuration<T> build() {
        return new Configuration<>(tokenizer, searchElementLoader);
    }

}
