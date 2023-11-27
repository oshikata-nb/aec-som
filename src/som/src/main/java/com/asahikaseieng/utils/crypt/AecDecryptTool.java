/*
 * Created on 2007/04/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import org.apache.commons.lang.StringUtils;

/**
 * 復号化後、標準出力する.
 * @author jbd
 */
public final class AecDecryptTool {

	/**
	 * コンストラクタ.
	 */
	private AecDecryptTool() {
	}

	/**
	 * コマンドライン引数の文字列を復号化し、標準出力に出力する.
	 * @param args args
	 */
	public static void main(final String[] args) {

		if (1 > args.length || StringUtils.isEmpty(args[0])) {
			System.out.printf("Usage : %s [Encrypted String]\r\n",
				AecDecryptTool.class.getName());
			System.exit(1);
			return;
		}
		try {
			System.out.println(AecCrypt.decrypt(args[0]));
		} catch (DecryptException e) {
			System.out.println("error : It failed in the decrypt.");
		}
	}
}
