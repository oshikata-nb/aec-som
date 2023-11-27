/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import junit.framework.TestCase;

/**
 * AecCryptのテストケース.
 * @author jbd
 */
public final class AecCryptTest extends TestCase {

	/**
	 * Constructor for CryptTest.
	 * 
	 * @param testname testname
	 */
	public AecCryptTest(final String testname) {
		super(testname);
	}

	/**
	 * 暗号化,複合化成功時のテスト.
	 * 
	 */
	public void testCryptDecrypt() {
		
		try {
			AecCrypt.encrypt(null);
			fail("Should raise a " + RuntimeException.class);
		} catch (RuntimeException e) {
			;
		}

		try {

			for (int i = 0; i < 500; i++) {
				assertEquals("" + i, AecCrypt.decrypt(AecCrypt.encrypt("" + i)));
			}
			assertEquals("kajihara", AecCrypt.decrypt(AecCrypt
					.encrypt("kajihara")));
		} catch (DecryptException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 複合化失敗時のテスト.
	 */
	public void testDecryptNG() {
		try {
			AecCrypt.decrypt(null);
			fail("Should raise a " + DecryptException.class);
		} catch (DecryptException e) {
			;
		}

		try {
			AecCrypt.decrypt("hogehogehogeha");
			fail("Should raise a DecryptFailedException");
		} catch (DecryptException e) {
		}
	}
}
