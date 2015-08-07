package com.ck.autocomplete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;

import com.ck.autocomplete.suffixtree.SuffixTreeSearch;
import com.ck.autocomplete.utility.StringNormalizer;

public class AutoCompleteSearch<TModel> implements IAutoCompleteSearch<TModel> {

	public static int AUTOCOMPLETE_MAX_RESULT_COUNT = 30;

	private SuffixTreeSearch<TModel> treeSearch;
	private StringNormalizer normalizer;

	public AutoCompleteSearch(SuffixTreeSearch<TModel> treeSearch, StringNormalizer normalizer) {
		this.treeSearch = treeSearch;
		this.normalizer = normalizer;
	}

	@Override
	public List<SearchElement<TModel>> getSearchResults(String words) {
		String[] normalizedWords = words.split(" ");
		for (int i = 0; i < normalizedWords.length; i++) {
			normalizedWords[i] = normalizer.normalize(normalizedWords[i]);
		}

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
