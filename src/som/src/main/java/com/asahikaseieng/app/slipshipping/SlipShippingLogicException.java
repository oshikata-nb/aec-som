/*
 * Created on 2009/02/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipshipping;

/**
 * 製造指図－ロジック処理例外
 * @author tosco
 */
public class SlipShippingLogicException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** エラーメッセージキー */
	private String key;

	/** 置換メッセージキー */
	private String[] replacements;

	/**
	 * コンストラクタ
	 */
	public SlipShippingLogicException() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 * @param cause エラー原因
	 */
	public SlipShippingLogicException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public SlipShippingLogicException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * @param cause エラー原因
	 */
	public SlipShippingLogicException(final Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * @param key エラーメッセージキー
	 * @param objects 置換メッセージキー
	 */
	public SlipShippingLogicException(final String key, final String... objects) {
		this.key = key;
		setReplacements(objects);
	}

	/**
	 * エラーメッセージキーを取得します。
	 * @return エラーメッセージキー
	 */
	public String getKey() {
		return key;
	}

	/**
	 * エラーメッセージキーを設定します。
	 * @param key エラーメッセージキー
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * 置換メッセージキーを取得します。
	 * @return 置換メッセージキー
	 */
	public String[] getReplacements() {
		return replacements;
	}

	/**
	 * 置換メッセージキーを設定します。
	 * @param replacements 置換メッセージキー
	 */
	public void setReplacements(final String[] replacements) {
		this.replacements = replacements;
	}

}
