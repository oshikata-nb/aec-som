/*
 * Created on 2008/01/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.zaictrl;

import java.math.BigDecimal;

/**
 * ##ここにクラスの説明を書いてください##
 * @author a1020630
 */
public final class StrCodeDto {

	/** Constructor */
	public StrCodeDto() {
	}

	/** lngFlg_PROCEDURE_PARAMETER */
	public static final String lngFlg_PROCEDURE_PARAMETER = "in";

	/** strCode_PROCEDURE_PARAMETER */
	public static final String strCode_PROCEDURE_PARAMETER = "in";

	/** strLoginUser_PROCEDURE_PARAMETER */
	public static final String strLoginUser_PROCEDURE_PARAMETER = "in";

	/** outPara_PROCEDURE_PARAMETER */
	public static final String outPara_PROCEDURE_PARAMETER = "out";

	private BigDecimal lngFlg;

	private String strCode;

	private String strLoginUser;

	private String outPara;

	/**
	 * コード・ゲッター
	 * @return String コード
	 */
	public String getStrCode() {
		return strCode;
	}

	/**
	 * コード・セッター
	 * @param strCode コード
	 */
	public void setStrCode(final String strCode) {
		this.strCode = strCode;
	}

	/**
	 * ログインユーザー・ゲッター
	 * @return String ログインユーザーコード
	 */
	public String getStrLoginUser() {
		return strLoginUser;
	}

	/**
	 * ログインユーザー・セッター
	 * @param strLoginUser ログインユーザーコード
	 */
	public void setStrLoginUser(final String strLoginUser) {
		this.strLoginUser = strLoginUser;
	}

	/**
	 * 出力パラメータのセッター
	 * @param outPara outPara
	 */
	public void setOutPara(final String outPara) {
		this.outPara = outPara;
	}

	/**
	 * 出力パラメータのゲッター
	 * @return outPara
	 */
	public String getOutPara() {
		return this.outPara;
	}

	/**
	 * lngFlgを取得します。
	 * @return lngFlg
	 */
	public BigDecimal getLngFlg() {
		return lngFlg;
	}

	/**
	 * lngFlgを設定します。
	 * @param lngFlg lngFlg
	 */
	public void setLngFlg(final BigDecimal lngFlg) {
		this.lngFlg = lngFlg;
	}

}
