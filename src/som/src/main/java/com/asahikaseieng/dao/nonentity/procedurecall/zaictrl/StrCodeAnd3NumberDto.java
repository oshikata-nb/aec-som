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
public final class StrCodeAnd3NumberDto {

	/** Constructor */
	public StrCodeAnd3NumberDto() {
	}

	/** lngFlg_PROCEDURE_PARAMETER */
	public static final String lngFlg_PROCEDURE_PARAMETER = "in";

	/** lngRowNo_PROCEDURE_PARAMETER */
	public static final String strCode_PROCEDURE_PARAMETER = "in";

	/** lngRowNo_PROCEDURE_PARAMETER */
	public static final String lngRowNo_PROCEDURE_PARAMETER = "in";

	/** lngRowNo_PROCEDURE_PARAMETER */
	public static final String lngStepNo_PROCEDURE_PARAMETER = "in";

	/** lngRowNo_PROCEDURE_PARAMETER */
	public static final String lngLineNo_PROCEDURE_PARAMETER = "in";

	/** strLoginUser_PROCEDURE_PARAMETER */
	public static final String strLoginUser_PROCEDURE_PARAMETER = "in";

	/** outPara_PROCEDURE_PARAMETER */
	public static final String outPara_PROCEDURE_PARAMETER = "out";

	private BigDecimal lngFlg;

	private String strCode;

	private BigDecimal lngRowNo;

	private BigDecimal lngStepNo;

	private BigDecimal lngLineNo;

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
	 * lngRowNoを取得します。
	 * @return lngRowNo 行番号
	 */
	public BigDecimal getLngRowNo() {
		return lngRowNo;
	}

	/**
	 * lngRowNoを設定します。
	 * @param lngRowNo 行番号
	 */
	public void setLngRowNo(final BigDecimal lngRowNo) {
		this.lngRowNo = lngRowNo;
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

	/**
	 * lngLineNoを取得します。
	 * @return lngLineNo
	 */
	public BigDecimal getLngLineNo() {
		return lngLineNo;
	}

	/**
	 * lngLineNoを設定します。
	 * @param lngLineNo lngLineNo
	 */
	public void setLngLineNo(final BigDecimal lngLineNo) {
		this.lngLineNo = lngLineNo;
	}

	/**
	 * lngStepNoを取得します。
	 * @return lngStepNo
	 */
	public BigDecimal getLngStepNo() {
		return lngStepNo;
	}

	/**
	 * lngStepNoを設定します。
	 * @param lngStepNo lngStepNo
	 */
	public void setLngStepNo(final BigDecimal lngStepNo) {
		this.lngStepNo = lngStepNo;
	}

}
