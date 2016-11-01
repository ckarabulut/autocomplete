package com.ck.autocomplete;

import com.ck.autocomplete.comparator.SearchElementComparator;
import com.ck.autocomplete.configuration.Configuration;
import com.ck.autocomplete.element.SearchElement;
import com.ck.autocomplete.suffixtree.SuffixTreeFactory;
import com.ck.autocomplete.suffixtree.SuffixTreeSearch;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

public class SearchImpl<TModel> implements Search<TModel> {

    private final Configuration<TModel> configuration;
    private final SuffixTreeFactory<TModel> suffixTreeFactory;

    private SuffixTreeSearch<TModel> treeSearch;

    public SearchImpl(Configuration<TModel> configuration) {
        this.configuration = configuration;
        this.suffixTreeFactory = new SuffixTreeFactory<>(configuration.getSearchElementLoader());
        this.treeSearch = new SuffixTreeSearch<>(suffixTreeFactory.create());
    }

    @Override
    public List<SearchElement<TModel>> search(String words) {
        String[] normalizedWords = configuration.getTokenizer().tokenize(words);
        Map<Integer, Set<SearchElement<TModel>>> resultMap = treeSearch.search(normalizedWords);

        List<SearchElement<TModel>> resultList = getSortedResult(resultMap);
        return resultList;
    }

    @Override
    public void reindex() {
        this.treeSearch = new SuffixTreeSearch<>(suffixTreeFactory.create());
    }

    private List<SearchElement<TModel>> getSortedResult(Map<Integer, Set<SearchElement<TModel>>> resultMap) {
        Comparator<SearchElement<TModel>> searchElementComparator = new SearchElementComparator<TModel>();

        Set<SearchElement<TModel>> sortedSet = new TreeSet<SearchElement<TModel>>(searchElementComparator);

        SortedMap<Integer, Set<SearchElement<TModel>>> sortedResultMap = new ConcurrentSkipListMap<Integer, Set<SearchElement<TModel>>>(resultMap);
        List<SearchElement<TModel>> resultList = new ArrayList<SearchElement<TModel>>();
        for (Entry<Integer, Set<SearchElement<TModel>>> entry : sortedResultMap.entrySet()) {
            Set<SearchElement<TModel>> currentSet = entry.getValue();
            currentSet.removeAll(resultList);

            sortedSet.clear();
            sortedSet.addAll(currentSet);

            resultList.addAll(sortedSet);
            if (resultList.size() >= configuration.getDefaultMaxSearchResult()) {
                break;
            }
        }
        if (resultList.size() > configuration.getDefaultMaxSearchResult()) {
            return resultList.subList(0, configuration.getDefaultMaxSearchResult());
        }
        return resultList;
    }

}
