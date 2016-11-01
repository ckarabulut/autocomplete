package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.element.SearchElement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SuffixTreeSearch<TModel> {

    public final static int AUTOCOMPLETE_MAX_KEYWORD_LENGHT = 20;

    private final SuffixTree<TModel> suffixTree;

    public SuffixTreeSearch(SuffixTree<TModel> suffixTree) {
        this.suffixTree = suffixTree;
    }

    /**
     * Search with given words and return Search Elements bounded by given maxResultCount.
     *
     * @return
     */
    public Map<Integer, Set<SearchElement<TModel>>> search(String[] normalizedWords) {
        if (normalizedWords.length == 0) {
            return new HashMap<>();
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
        SuffixTreeNode<TModel> currentNode = suffixTree.getRoot();
        for (int i = 0; i < chars.length; i++) {
            SuffixTreeNode<TModel> child = currentNode.getChild(chars[i]);
            if (child == null || !(child instanceof SuffixTreeNode)) {
                return new HashMap<Integer, Set<SearchElement<TModel>>>();
            }
            currentNode = (SuffixTreeNode<TModel>) child;
        }
        HashMap<Integer, Set<SearchElement<TModel>>> resultMap = new HashMap<Integer, Set<SearchElement<TModel>>>();
        setTailsResults(currentNode, resultMap);
        return resultMap;
    }

    private void setTailsResults(SuffixTreeNode<TModel> currentNode, Map<Integer, Set<SearchElement<TModel>>> resultMap) {
        for (SuffixTreeNode<TModel> node : currentNode.getChilds()) {
            if (node instanceof SuffixTreeNode) {
                mergeSetOfMaps(resultMap, ((SuffixTreeNode<TModel>) node).getValueMap());
            } else {
                setTailsResults((SuffixTreeNode<TModel>) node, resultMap);
            }
        }
    }

    /**
     * Merge given map of Search Element Sets with an upper bound count.
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
