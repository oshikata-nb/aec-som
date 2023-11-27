/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;


/**
 * 数値桁数チェック例外クラス
 * @author tosco
 */
public class CheckDigitException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** エラーコード */
	private int code;

	/**
	 * コンストラクタ
	 */
	public CheckDigitException() {
	}

	/**
	 * コンストラクタ
	 * @param message メッセージ
	 */
	public CheckDigitException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * @param cause 原因の例外
	 */
	public CheckDigitException(final Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * @param message メッセージ
	 * @param cause 原因の例外
	 */
	public CheckDigitException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * コンストラクタ
	 * @param message メッセージ
	 * @param code エラーコード
	 */
	public CheckDigitException(final String message, final int code) {
		super(message);
		this.code = code;
	}
	/**
	 * コンストラクタ
	 * @param code エラーコード
	 */
	public CheckDigitException(final int code) {
		this.code = code;
	}

	/**
	 * エラーコードを取得します。
	 * @return エラーコード
	 */
	public int getCode() {
		return code;
	}

	/**
	 * エラーコードを設定します。
	 * @param code エラーコード
	 */
	public void setCode(final int code) {
		this.code = code;
	}
}
