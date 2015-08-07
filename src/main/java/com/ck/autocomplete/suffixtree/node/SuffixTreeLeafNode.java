package com.ck.autocomplete.suffixtree.node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ck.autocomplete.SearchElement;

public class SuffixTreeLeafNode<TModel> extends SuffixTreeNode<TModel> {

	private Map<Integer, Set<SearchElement<TModel>>> valueMap;

	public SuffixTreeLeafNode() {
		super('$');
		valueMap = new HashMap<Integer, Set<SearchElement<TModel>>>();
	}

	public void addSearchElement(int index, SearchElement<TModel> value) {
		if (!valueMap.containsKey(index)) {
			valueMap.put(index, new HashSet<SearchElement<TModel>>());
		}
		Set<SearchElement<TModel>> values = valueMap.get(index);
		values.add(value);
	}

	public Map<Integer, Set<SearchElement<TModel>>> getValueMap() {
		return valueMap;
	}

}
