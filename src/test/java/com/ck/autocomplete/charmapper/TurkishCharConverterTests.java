package com.ck.autocomplete.charmapper;

import org.junit.Assert;
import org.junit.Test;

public class TurkishCharConverterTests {

	@Test
	public void convertAllChars() {
		TurkishCharConverter converter = new TurkishCharConverter();
		Assert.assertEquals("Must be CcSsIiOoUuGg", "CcSsIiOoUuGg", converter.convertAll("ÇçŞşİıÖöÜüĞğ"));
	}

}
