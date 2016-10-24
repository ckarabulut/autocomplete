package com.ck.autocomplete.mapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TurkishCharMapperTests {

    @Test
    public void test_initialize() {
        assertNotNull(new TurkishCharMapper());
    }

    @Test
    public void test_convert_all_chars() {
        CharMapper mapper = new TurkishCharMapper();
        assertEquals("CcSsIiOoUuGg", mapper.mapAll("ÇçŞşİıÖöÜüĞğ"));
        assertEquals("AAbbaB c u i g o s C U I G O S test", mapper.mapAll("AAbbaB ç ü ı ğ ö ş Ç Ü İ Ğ Ö Ş test"));
    }

}
