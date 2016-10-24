package com.ck.autocomplete;

import com.ck.autocomplete.comparator.SearchElementComparator;
import com.ck.autocomplete.configuration.Configuration;
import com.ck.autocomplete.configuration.Tokenizer;
import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.element.SearchElement;
import com.ck.autocomplete.suffixtree.SuffixTreeSearch;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

public class SearchImpl<TModel> implements Search<TModel> {

    public static int AUTOCOMPLETE_MAX_RESULT_COUNT = 30;

    private final SuffixTreeSearch<TModel> treeSearch;
    private final Tokenizer tokenizer;
    private final SearchElementLoader<TModel> elementLoader;

    public SearchImpl(Configuration configuration) {
        this.tokenizer = configuration.getTokenizer();
        this.elementLoader = configuration.getSearchElementLoader();
        this.treeSearch = null;
    }

    @Override
    public List<SearchElement<TModel>> search(String words) {
        String[] normalizedWords = tokenizer.tokenize(words);
        Map<Integer, Set<SearchElement<TModel>>> resultMap = treeSearch.search(normalizedWords);

        List<SearchElement<TModel>> resultList = getSortedResult(resultMap);
        return resultList;
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
            if (resultList.size() >= AUTOCOMPLETE_MAX_RESULT_COUNT) {
                break;
            }
        }
        if (resultList.size() > AUTOCOMPLETE_MAX_RESULT_COUNT) {
            return resultList.subList(0, AUTOCOMPLETE_MAX_RESULT_COUNT);
        }
        return resultList;
    }

}
