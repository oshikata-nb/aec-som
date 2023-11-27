/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 販売条件・見積単価コピー・削除確定or確定取消処理
 * @author T1344224
 */
public final class ProConSalestermsAndEstimateCallDto {

	/** Constructor */
	public ProConSalestermsAndEstimateCallDto() {
	}

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";
	
	/** pPkNo_PROCEDURE_PARAMETER */
	public static final String pPkNo_PROCEDURE_PARAMETER = "in";
	
	/** pConfirmFlg_PROCEDURE_PARAMETER */
	public static final String pConfirmFlg_PROCEDURE_PARAMETER = "in";
	
	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrTantoCd;
	
	private String pPkNo;
	
	private String pConfirmFlg;

	private String pStrErrorCd;

	private String pStrErrorMsg;

	private BigDecimal pNumRet;

	/**
	 * 戻り値を取得
	 * @return BigDecimal 戻り値
	 */
	public BigDecimal getPNumRet() {
		return pNumRet;
	}

	/**
	 * 戻り値設定
	 * @param numRet 戻り値
	 */
	public void setPNumRet(final BigDecimal numRet) {
		pNumRet = numRet;
	}

	/**
	 * エラーコード取得
	 * @return String エラーコード
	 */
	public String getPStrErrorCd() {
		return pStrErrorCd;
	}

	/**
	 * エラーコード
	 * @param strErrorCd エラーコード
	 */
	public void setPStrErrorCd(final String strErrorCd) {
		pStrErrorCd = strErrorCd;
	}

	/**
	 * エラーメッセージ取得
	 * @return String エラーメッセージ
	 */
	public String getPStrErrorMsg() {
		return pStrErrorMsg;
	}

	/**
	 * エラーメッセージ設定
	 * @param strErrorMsg エラーメッセージ
	 */
	public void setPStrErrorMsg(final String strErrorMsg) {
		pStrErrorMsg = strErrorMsg;
	}

	/**
	 * 担当コード取得
	 * @return String 担当コード
	 */
	public String getPStrTantoCd() {
		return pStrTantoCd;
	}

	/**
	 * 担当コード設定
	 * @param strTantoCd 担当コード
	 */
	public void setPStrTantoCd(final String strTantoCd) {
		pStrTantoCd = strTantoCd;
	}

	/**
	 * pPkNoを取得します。
	 * @return pPkNo
	 */
	public String getpPkNo() {
		return pPkNo;
	}

	/**
	 * pPkNoを設定します。
	 * @param pPkNo pPkNo
	 */
	public void setpPkNo(final String pPkNo) {
		this.pPkNo = pPkNo;
	}


	/**
	 * pConfirmFlgを取得します。
	 * @return pConfirmFlg
	 */
	public String getpConfirmFlg() {
		return pConfirmFlg;
	}

	/**
	 * pConfirmFlgを設定します。
	 * @param pConfirmFlg pConfirmFlg
	 */
	public void setpConfirmFlg(final String pConfirmFlg) {
		this.pConfirmFlg = pConfirmFlg;
	}
}
