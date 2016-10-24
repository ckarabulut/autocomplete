package com.ck.autocomplete.element;

import com.ck.autocomplete.configuration.StringTokenizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchElement<TModel> {

    private final TModel value;
    private final String indexes;
    private final Set<String> indexSet;

    public SearchElement(TModel value, String keys, StringTokenizer tokenizer) {
        this.value = value;
        String[] tokenize = tokenizer.tokenize(keys);
        this.indexes = Arrays.toString(tokenize);
        this.indexSet = new HashSet<>(Arrays.asList(tokenize));
    }

    public TModel getValue() {
        return value;
    }

    public Set<String> getIndexSet() {
        return indexSet;
    }

    public String getIndexes() {
        return indexes;
    }
}
