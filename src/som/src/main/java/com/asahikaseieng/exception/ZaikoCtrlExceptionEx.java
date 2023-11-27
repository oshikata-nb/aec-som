/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

/**
 * 在庫プロシージャで発生した業務的な例外クラス.
 * @author jbd
 */
public class ZaikoCtrlExceptionEx extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** エラーメッセージ(内部用) */
	private String insideErrMsg;

	/** エラー発生モジュール */
	private String moduleCd;

	/** ユーザー名 */
	private String user;

	/**
	 * コンストラクタ.
	 */
	public ZaikoCtrlExceptionEx() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param mes エラーメッセージ
	 */
	public ZaikoCtrlExceptionEx(final String mes) {
		super(mes);
	}

	/**
	 * userを取得します。
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * userを設定します。
	 * @param user user
	 */
	public void setUser(final String user) {
		this.user = user;
	}

	/**
	 * insideErrMsgを取得します。
	 * @return insideErrMsg
	 */
	public String getInsideErrMsg() {
		return insideErrMsg;
	}

	/**
	 * insideErrMsgを設定します。
	 * @param insideErrMsg insideErrMsg
	 */
	public void setInsideErrMsg(final String insideErrMsg) {
		this.insideErrMsg = insideErrMsg;
	}

	/**
	 * moduleCdを取得します。
	 * @return moduleCd
	 */
	public String getModuleCd() {
		return moduleCd;
	}

	/**
	 * moduleCdを設定します。
	 * @param moduleCd moduleCd
	 */
	public void setModuleCd(final String moduleCd) {
		this.moduleCd = moduleCd;
	}

}
