/*
 * Created on 2007/04/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

import org.apache.commons.lang.StringUtils;

/**
 * 暗号化後、標準出力する.
 * @author jbd
 */
public final class AecCryptTool {

	/**
	 * コンストラクタ.
	 */
	private AecCryptTool() {
	}

	/**
	 * コマンドライン引数の文字列を暗号化し、標準出力に出力する.
	 * @param args args
	 */
	public static void main(final String[] args) {

		if (1 > args.length || StringUtils.isEmpty(args[0])) {
			System.out.printf("Usage : %s [String]\r\n",  AecCryptTool.class.getName());
			System.exit(1);
			return;
		}

		System.out.println(AecCrypt.encrypt(args[0]));
	}

}
