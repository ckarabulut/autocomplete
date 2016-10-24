package com.ck.autocomplete.utility;

/**
 * Created by caglar on 20-Sep-16.
 */
public interface Cache<T> {
    T getInstance();
    void refresh();
}
