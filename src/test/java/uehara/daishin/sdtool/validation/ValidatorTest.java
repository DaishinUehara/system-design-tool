package uehara.daishin.sdtool.validation;

import junit.framework.TestCase;

public class ValidatorTest extends TestCase {

	public void testValidation01_01() {
		assertFalse(Validator.validation("1", ValidationType.DateYYYYMMDD1));
	}

	public void testValidation01_02() {
		assertTrue(Validator.validation("20181102", ValidationType.DateYYYYMMDD1));
	}
}
