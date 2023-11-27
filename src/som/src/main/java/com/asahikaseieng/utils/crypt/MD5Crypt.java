/*
 * Created on 2008/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5暗号化クラス
 * 
 * @author tosco
 */
public final class MD5Crypt {

	//MD5 メッセージダイジェストアルゴリズム
	private static final String ALGORITHM = "MD5";

	//文字セット
	private static final String CHARSETNAME = "UTF-8";

	//16進数
	private static final int HEXADECIMAL_NUMBER = 0xff;

	private MD5Crypt() {
	}

	/**
	 * MD5暗号化
	 * 
	 * @param str 暗号化する文字列
	 * @return String 暗号化された文字列
	 * @throws Exception Exception
	 */
	public static String getMD5String(final String str) throws Exception {
		StringBuffer sb = new StringBuffer();

		if (str != null && !str.equals("")) {
			try {
				MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);

				byte[] md5Byte = md5.digest(str.getBytes(CHARSETNAME));
				md5.reset();

				for (int i = 0; i < md5Byte.length; i++) {
					String b = Integer.toHexString(md5Byte[i] & HEXADECIMAL_NUMBER);
					if (b.length() == 1) {
						sb.append("0");
					}
					sb.append(b);
				}

			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}
		return sb.toString();
	}

}
