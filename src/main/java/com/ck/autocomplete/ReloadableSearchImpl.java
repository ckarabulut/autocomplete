package com.ck.autocomplete;

import com.ck.autocomplete.configuration.StringTokenizer;
import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.element.SearchElement;
import com.ck.autocomplete.mapper.CharMapper;
import com.ck.autocomplete.suffixtree.SuffixTreeCache;
import com.ck.autocomplete.suffixtree.SuffixTreeSearch;

import java.util.List;

public class ReloadableSearchImpl<TModel> implements ReloadableSearch<TModel> {

    private SuffixTreeCache<TModel> suffixTreeCache;
    private Search<TModel> search;

    public ReloadableSearchImpl(SearchElementLoader<TModel> elementLoader) {
        this.suffixTreeCache = new SuffixTreeCache<TModel>(elementLoader);
        SuffixTreeSearch<TModel> treeSearch = new SuffixTreeSearch<TModel>(suffixTreeCache);
        this.search = new SearchImpl<TModel>(null);
    }

    public ReloadableSearchImpl(SearchElementLoader<TModel> elementLoader, CharMapper charMapper) {
        this.suffixTreeCache = new SuffixTreeCache<TModel>(elementLoader);
        SuffixTreeSearch<TModel> treeSearch = new SuffixTreeSearch<TModel>(suffixTreeCache);
        StringTokenizer normalizer = new StringTokenizer(charMapper);
        this.search = new SearchImpl<TModel>(null);
    }

    @Override
    public List<SearchElement<TModel>> search(String words) {
        return search.search(words);
    }

    @Override
    public void reloadModels() {
        suffixTreeCache.refresh();
    }

}
