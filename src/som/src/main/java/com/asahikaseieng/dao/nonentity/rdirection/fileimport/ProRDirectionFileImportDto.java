/*
 * Created on 2022/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection.fileimport;

import java.math.BigDecimal;

/**
 * ProcedureCallクラス.DTO
 * @author 
 */
public final class ProRDirectionFileImportDto {

	/** Constructor */
	public ProRDirectionFileImportDto() {
	}

	/** pCarryCd_PROCEDURE_PARAMETER */
	public static final String pTempNo_PROCEDURE_PARAMETER = "in";

	/** pShippingNoList_PROCEDURE_PARAMETER */
	public static final String pTantoCd_PROCEDURE_PARAMETER = "in";
	
	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pTempNo;

	private String pTantoCd;
	
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
	 * pTempNoを取得します。
	 * @return pTempNo
	 */
	public String getpTempNo() {
		return pTempNo;
	}

	/**
	 * pTempNoを設定します。
	 * @param pTempNo pTempNo
	 */
	public void setpTempNo(String pTempNo) {
		this.pTempNo = pTempNo;
	}

	/**
	 * pTantoCdを取得します。
	 * @return pTantoCd
	 */
	public String getpTantoCd() {
		return pTantoCd;
	}

	/**
	 * pTantoCdを設定します。
	 * @param pTantoCd pTantoCd
	 */
	public void setpTantoCd(String pTantoCd) {
		this.pTantoCd = pTantoCd;
	}

}
