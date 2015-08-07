package com.ck.autocomplete.suffixtree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import com.ck.autocomplete.SearchElement;
import com.ck.autocomplete.suffixtree.node.SuffixTreeInternalNode;
import com.ck.autocomplete.suffixtree.node.SuffixTreeLeafNode;
import com.ck.autocomplete.suffixtree.node.SuffixTreeNode;

import java.util.Set;

public class SuffixTreeSearch<TModel> {

	public final static int AUTOCOMPLETE_MAX_KEYWORD_LENGHT = 20;

	private final SuffixTreeCache<TModel> suffixTreeCache;

	public SuffixTreeSearch(SuffixTreeCache<TModel> suffixTreeCache) {
		this.suffixTreeCache = suffixTreeCache;
	}

	/**
	 * Search with given words and return Search Elements bounded by given maxResultCount.
	 * 
	 * @param normalizedWords
	 * @param maxResultCount
	 * @return
	 */
	public Map<Integer, Set<SearchElement<TModel>>> search(String[] normalizedWords) {
		if (normalizedWords.length == 0) {
			return new HashMap<Integer, Set<SearchElement<TModel>>>();
		}

		Map<Integer, Set<SearchElement<TModel>>> allResults = searchWord(normalizedWords[0]);
		for (int i = 1; i < normalizedWords.length; i++) {
			Map<Integer, Set<SearchElement<TModel>>> result = searchWord(normalizedWords[i]);
			allResults = createIntersectionSetOfMaps(allResults, result);
		}
		return allResults;
	}

	private Map<Integer, Set<SearchElement<TModel>>> searchWord(String word) {
		if (word.length() > AUTOCOMPLETE_MAX_KEYWORD_LENGHT) {
			word = word.substring(0, AUTOCOMPLETE_MAX_KEYWORD_LENGHT);
		}

		char[] chars = word.toCharArray();
		SuffixTree<TModel> instance = suffixTreeCache.getInstance();
		SuffixTreeInternalNode<TModel> currentNode = instance.getRoot();
		for (int i = 0; i < chars.length; i++) {
			SuffixTreeNode<TModel> child = currentNode.getChild(chars[i]);
			if (child == null || !(child instanceof SuffixTreeInternalNode)) {
				return new HashMap<Integer, Set<SearchElement<TModel>>>();
			}
			currentNode = (SuffixTreeInternalNode<TModel>) child;
		}
		HashMap<Integer, Set<SearchElement<TModel>>> resultMap = new HashMap<Integer, Set<SearchElement<TModel>>>();
		setTailsResults(currentNode, resultMap);
		return resultMap;
	}

	private void setTailsResults(SuffixTreeInternalNode<TModel> currentNode, Map<Integer, Set<SearchElement<TModel>>> resultMap) {
		for (SuffixTreeNode<TModel> node : currentNode.getChilds()) {
			if (node instanceof SuffixTreeLeafNode) {
				mergeSetOfMaps(resultMap, ((SuffixTreeLeafNode<TModel>) node).getValueMap());
			} else {
				setTailsResults((SuffixTreeInternalNode<TModel>) node, resultMap);
			}
		}
	}

	/**
	 * Merge given map of Search Element Sets with an upper bound count.
	 * 
	 * @param baseList
	 * @param appendantList
	 * @param maxElementCount
	 */
	public void mergeSetOfMaps(Map<Integer, Set<SearchElement<TModel>>> baseMap, Map<Integer, Set<SearchElement<TModel>>> appendantMap) {
		for (Entry<Integer, Set<SearchElement<TModel>>> entry : appendantMap.entrySet()) {
			int key = entry.getKey();
			if (!baseMap.containsKey(key)) {
				baseMap.put(key, new HashSet<SearchElement<TModel>>());
			}
			Set<SearchElement<TModel>> baseSet = baseMap.get(key);
			baseSet.addAll(entry.getValue());
		}
	}

	/**
	 * Take intersection parts of two map of sets
	 * 
	 * @param baseList
	 * @param appendantList
	 * @param maxElementCount
	 */
	public Map<Integer, Set<SearchElement<TModel>>> createIntersectionSetOfMaps(Map<Integer, Set<SearchElement<TModel>>> baseMap, Map<Integer, Set<SearchElement<TModel>>> appendantMap) {

		Map<SearchElement<TModel>, Integer> baseElementMap = getElementMap(baseMap);
		Map<SearchElement<TModel>, Integer> appendantElementMap = getElementMap(appendantMap);

		baseElementMap.keySet().retainAll(appendantElementMap.keySet());

		Map<Integer, Set<SearchElement<TModel>>> intersectionMap = new HashMap<Integer, Set<SearchElement<TModel>>>();

		for (Entry<SearchElement<TModel>, Integer> entry : baseElementMap.entrySet()) {
			int key = entry.getValue() > appendantElementMap.get(entry.getKey()) ? appendantElementMap.get(entry.getKey()) : entry.getValue();
			if (!intersectionMap.containsKey(key)) {
				intersectionMap.put(key, new HashSet<SearchElement<TModel>>());
			}
			intersectionMap.get(key).add(entry.getKey());
		}
		return intersectionMap;
	}

	private Map<SearchElement<TModel>, Integer> getElementMap(Map<Integer, Set<SearchElement<TModel>>> map) {
		Map<SearchElement<TModel>, Integer> elementMap = new HashMap<SearchElement<TModel>, Integer>();
		for (Entry<Integer, Set<SearchElement<TModel>>> entry : map.entrySet()) {
			for (SearchElement<TModel> element : entry.getValue()) {
				if (!elementMap.containsKey(element) || elementMap.get(element) > entry.getKey()) {
					elementMap.put(element, entry.getKey());
				}
			}
		}
		return elementMap;
	}

}
