package com.ck.autocomplete;

import java.util.HashSet;
import java.util.Set;

import com.ck.autocomplete.utility.StringNormalizer;

public class SearchElement<TModel> {

	private final TModel value;
	private final String normalizedKeys;
	private final Set<String> searchIndexSet;

	public SearchElement(TModel value, String keys, StringNormalizer normalizer) {
		this.value = value;
		this.normalizedKeys = normalizer.normalizeWithWhiteSpace(keys);
		this.searchIndexSet = new HashSet<String>();

		String[] searchIndexes = keys.split(" ");
		for (String searchIndex : searchIndexes) {
			String normalizedString = normalizer.normalize(searchIndex);
			if (!normalizedString.isEmpty()) {
				searchIndexSet.add(normalizedString);
			}
		}
	}

	public TModel getValue() {
		return value;
	}

	public String getNormalizedKeys() {
		return normalizedKeys;
	}

	public Set<String> getSearchIndexSet() {
		return searchIndexSet;
	}

}
