/*
 * Created on 2008/04/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import org.apache.commons.lang.StringUtils;

/**
 * MD5暗号化後、標準出力する.
 * @author tosco
 */
public final class MD5CryptTool {

	/**
	 * コンストラクタ.
	 */
	private MD5CryptTool() {
	}

	/**
	 * コマンドライン引数の文字列を暗号化し、標準出力に出力する.
	 * @param args args
	 * @throws Exception Exception
	 */
	public static void main(final String[] args) throws Exception {

		if (1 > args.length || StringUtils.isEmpty(args[0])) {
			System.out.printf("Usage : %s [String]\r\n",  MD5CryptTool.class.getName());
			System.exit(1);
			return;
		}

		System.out.println(MD5Crypt.getMD5String(args[0]));
	}

}
