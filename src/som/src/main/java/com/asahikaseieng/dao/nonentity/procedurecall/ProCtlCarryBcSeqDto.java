/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 運送会社バーコード用シーケンス作成DTO
 * @author Kiguchi
 */
public final class ProCtlCarryBcSeqDto {

	/** Constructor */
	public ProCtlCarryBcSeqDto() {
	}

	/** pCarryCd_PROCEDURE_PARAMETER */
	public static final String pCarryCd_PROCEDURE_PARAMETER = "in";

	/** pMinValue_PROCEDURE_PARAMETER */
	public static final String pMinValue_PROCEDURE_PARAMETER = "in";

	/** pMaxValue_PROCEDURE_PARAMETER */
	public static final String pMaxValue_PROCEDURE_PARAMETER = "in";

	/** pCurValue_PROCEDURE_PARAMETER */
	public static final String pCurValue_PROCEDURE_PARAMETER = "in";

	/** pDelFlg_PROCEDURE_PARAMETER */
	public static final String pDelFlg_PROCEDURE_PARAMETER = "in";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pCarryCd;

	private BigDecimal pMinValue;

	private BigDecimal pMaxValue;

	private BigDecimal pCurValue;

	private BigDecimal pDelFlg;

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
	 * 最小値取得
	 * @return BigDecimal 最小値
	 */
	public BigDecimal getPMinValue() {
		return pMinValue;
	}

	/**
	 * 最小値設定
	 * @param pMinValue 最小値
	 */
	public void setPMinValue(final BigDecimal pMinValue) {
		this.pMinValue = pMinValue;
	}

	/**
	 * 最大値取得
	 * @return pMaxValue 最大値
	 */
	public BigDecimal getPMaxValue() {
		return this.pMaxValue;
	}

	/**
	 * 最大値設定
	 * @param pMaxValue 最大値
	 */
	public void setPMaxValue(final BigDecimal pMaxValue) {
		this.pMaxValue = pMaxValue;
	}
	
	/**
	 * 現在値設定
	 * @param pCurValue 現在値
	 */
	public void setPCurValue(final BigDecimal pCurValue) {
		this.pCurValue = pCurValue;
	}

	/**
	 * 現在値取得
	 * @return pCurValue 現在値
	 */
	public BigDecimal getPCurValue() {
		return this.pCurValue;
	}
	
	/**
	 * 削除フラグ取得
	 * @return String 運送会社コード
	 */
	public BigDecimal getPDelFlg() {
		return pDelFlg;
	}

	/**
	 * 削除フラグ設定
	 * @param pDelFlg 削除フラグ
	 */
	public void setPDelFlg(final BigDecimal pDelFlg) {
		this.pDelFlg = pDelFlg;
	}

	

}
