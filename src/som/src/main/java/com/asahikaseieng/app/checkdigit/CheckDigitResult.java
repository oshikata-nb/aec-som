/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import java.io.Serializable;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

/**
 * 数値桁数チェック結果
 * @author tosco
 */
public class CheckDigitResult implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 数値桁数チェック設定 */
	private NumberChkDisitDetail detail;
	/** エラーコード */
	private int code;

	/**
	 * コンストラクタ
	 */
	public CheckDigitResult() {
	}
	/**
	 * コンストラクタ
	 * @param code エラーコード
	 */
	public CheckDigitResult(final int code) {
		this(code, null);
	}
	/**
	 * コンストラクタ
	 * @param code エラーコード
	 * @param detail 桁数チェック設定
	 */
	public CheckDigitResult(final int code, final NumberChkDisitDetail detail) {
		this.code = code;
		this.detail = detail;
	}
	/**
	 * 数値桁数チェック設定を取得します。
	 * @return 数値桁数チェック設定
	 */
	public NumberChkDisitDetail getDetail() {
		return detail;
	}

	/**
	 * 数値桁数チェック設定を設定します。
	 * @param detail 数値桁数チェック設定
	 */
	public void setDetail(final NumberChkDisitDetail detail) {
		this.detail = detail;
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
