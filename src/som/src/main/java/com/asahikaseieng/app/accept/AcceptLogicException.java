/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

/**
 * 受入－ロジック処理例外
 * @author tosco
 */
public class AcceptLogicException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** エラーメッセージキー */
	private String key;

	/** 置換メッセージキー */
	private String[] replacements;

	/** エラーコード(内部用) */
	private String insideErrCd;

	/** エラーメッセージ(内部用) */
	private String insideErrMsg;

	/** エラー発生モジュール */
	private String moduleCd;

	/**
	 * コンストラクタ
	 */
	public AcceptLogicException() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 * @param cause エラー原因
	 */
	public AcceptLogicException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public AcceptLogicException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * @param cause エラー原因
	 */
	public AcceptLogicException(final Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * @param key エラーメッセージキー
	 * @param objects 置換メッセージキー
	 */
	public AcceptLogicException(final String key, final String... objects) {
		this.key = key;
		setReplacements(objects);
		this.insideErrCd = "";
		this.insideErrMsg = "";
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

	/**
	 * エラーコード(内部用)を取得します。
	 * @return エラーコード(内部用)
	 */
	public String getInsideErrCd() {
		return insideErrCd;
	}

	/**
	 * エラーコード(内部用)を設定します。
	 * @param insideErrCd エラーコード(内部用)
	 */
	public void setInsideErrCd(final String insideErrCd) {
		this.insideErrCd = insideErrCd;
	}

	/**
	 * エラーメッセージ(内部用)を取得します。
	 * @return エラーメッセージ(内部用)
	 */
	public String getInsideErrMsg() {
		return insideErrMsg;
	}

	/**
	 * エラーメッセージ(内部用)を設定します。
	 * @param insideErrMsg エラーメッセージ(内部用)
	 */
	public void setInsideErrMsg(final String insideErrMsg) {
		this.insideErrMsg = insideErrMsg;
	}

	/**
	 * エラー発生モジュールを取得します。
	 * @return エラー発生モジュール
	 */
	public String getModuleCd() {
		return moduleCd;
	}

	/**
	 * エラー発生モジュールを設定します。
	 * @param moduleCd エラー発生モジュール
	 */
	public void setModuleCd(final String moduleCd) {
		this.moduleCd = moduleCd;
	}
}
