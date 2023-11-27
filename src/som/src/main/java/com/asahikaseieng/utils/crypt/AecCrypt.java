/*
 * Created on 2007/04/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 暗号化クラス.
 * @author jbd
 */
final class AecCrypt {

	private AecCrypt() {
	}

	private static final byte[] SEED;

	private static final String ARG = "DES";

	private static final String ENCODING = "iso-8859-1";

	private static SecretKeySpec key;

	static {
		// 8byteである事に意味がある
		try {
			SEED = "katamari".getBytes(ENCODING);
		} catch (UnsupportedEncodingException ignore) {
			throw new RuntimeException(ignore);
		}
	}

	/**
	 * @param keyAlg 鍵アルゴリズム
	 * @param ranAlg SecureRandomのアルゴリズム
	 * @param seed 交換したSecureRandomのseed値
	 */
	private static synchronized SecretKeySpec genSecretKey() {
		try {
			if (key == null) {
				key = new SecretKeySpec(SEED, ARG);
			}
		} catch (Exception ignore) {
			throw new RuntimeException(ignore);
		}
		return key;
	}

	private static final int MASK = 0xff;

	/**
	 * 文字列を暗号化する.
	 * 
	 * @param org 元の文字列
	 * @return 暗号化された文字列
	 */
	public static String encrypt(final String org) {

		SecretKeySpec sk = genSecretKey();

		try {
			Cipher cipher = Cipher.getInstance(ARG);
			cipher.init(Cipher.ENCRYPT_MODE, sk);
			byte[] data = cipher.doFinal(org.getBytes(ENCODING));
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				String t = Integer.toHexString(data[i] & MASK);
				if (t.length() == 1) {
					t = "0" + t;
				}
				sb.append(t);
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 文字列を複合化する.
	 * 
	 * @param org 暗号化された文字列
	 * @return 復号化された文字列
	 * @throws DecryptException 複合化失敗時の例外
	 */
	public static String decrypt(final String org) throws DecryptException {

		String result = org;

		SecretKeySpec sk = genSecretKey();

		try {
			Cipher cipher = Cipher.getInstance(ARG);
			cipher.init(Cipher.DECRYPT_MODE, sk);
			byte[] b = new byte[result.length() / 2];
			for (int i = 0; i < result.length(); i += 2) {
				b[i / 2] = Short.decode("0x" + result.substring(i, i + 2))
						.byteValue();
			}
			byte[] data = cipher.doFinal(b);
			result = new String(data);
		} catch (Exception e) {
			throw new DecryptException(e);
		}

		return result;
	}
}
