package uehara.daishin.sdtool.validation;

import junit.framework.TestCase;

public class ValidatorTest extends TestCase {

	public void testValidation01_01() {
		assertFalse(Validator.validation("1", ValidationType.DateTimeYYYYMMDDHHMM1));
	}

	public void testValidation01_02() {
		assertTrue(Validator.validation("2018.11.02 23:59", ValidationType.DateTimeYYYYMMDDHHMM1));
	}
}
