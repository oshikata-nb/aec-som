/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 運送会社バーコード用シーケンス取得作成DTO
 * @author Kiguchi
 */
public final class ProGetCarryBcNextValDto {

	/** Constructor */
	public ProGetCarryBcNextValDto() {
	}

	/** pCarryCd_PROCEDURE_PARAMETER */
	public static final String pCarryCd_PROCEDURE_PARAMETER = "in";

	/** pNextVal_PROCEDURE_PARAMETER */
	public static final String pNextVal_PROCEDURE_PARAMETER = "out";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pCarryCd;

	private BigDecimal pNextVal;

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
	 * 運送会社コード取得
	 * @return String 運送会社コード
	 */
	public String getPCarryCd() {
		return pCarryCd;
	}

	/**
	 * 運送会社コード設定
	 * @param pCarryCd 出荷予定日
	 */
	public void setPCarryCd(final String pCarryCd) {
		this.pCarryCd = pCarryCd;
	}

	/**
	 * pNextValを取得します。
	 * @return pNextVal
	 */
	public BigDecimal getpNextVal() {
		return pNextVal;
	}

	/**
	 * pNextValを設定します。
	 * @param pNextVal pNextVal
	 */
	public void setpNextVal(BigDecimal pNextVal) {
		this.pNextVal = pNextVal;
	}

	

}
