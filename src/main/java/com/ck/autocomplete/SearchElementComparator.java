package com.ck.autocomplete;

import java.util.Comparator;

import com.ck.autocomplete.comparator.NatureOrderComparator;

public class SearchElementComparator<TModel> implements Comparator<SearchElement<TModel>> {

	private static NatureOrderComparator comparator;
	static {
		comparator = new NatureOrderComparator();
	}

	@Override
	public int compare(SearchElement<TModel> element1, SearchElement<TModel> element2) {
		String a = element1.getNormalizedKeys();
		String b = element2.getNormalizedKeys();

		int value = a.compareTo(b);
		if (value == 0) {
			return element1.getValue().equals(element2.getValue()) ? 0 : 1;
		}
		return comparator.compare(a, b);
	}
}
