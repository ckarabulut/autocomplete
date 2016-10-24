package com.ck.autocomplete.comparator;

import com.ck.autocomplete.element.SearchElement;

import java.util.Comparator;

public class SearchElementComparator<TModel> implements Comparator<SearchElement<TModel>> {

    private NatureOrderComparator comparator;

    public SearchElementComparator() {
        comparator = new NatureOrderComparator();
    }

    @Override
    public int compare(SearchElement<TModel> element1, SearchElement<TModel> element2) {
        String a = element1.getIndexes();
        String b = element2.getIndexes();

        if (a.equals(b)) {
            return element1.getValue().equals(element2.getValue()) ? 0 : 1;
        }
        return comparator.compare(a, b);
    }
}
