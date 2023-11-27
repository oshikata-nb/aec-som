/*
 * Created on 2020/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;


/**
 * 休日考慮の日付計算用Dtoクラス
 * @author tosco
 */
public final class ProCalcDateFromCalendarCallDto {

	/** Constructor */
	public ProCalcDateFromCalendarCallDto() {
	}

	/** pStrDate_PROCEDURE_PARAMETER */
	public static final String pStrDate_PROCEDURE_PARAMETER = "in";

	/** pLeadTime_PROCEDURE_PARAMETER */
	public static final String pLeadTime_PROCEDURE_PARAMETER = "in";

	/** pStrCalCd_PROCEDURE_PARAMETER */
	public static final String pStrCalCd_PROCEDURE_PARAMETER = "in";

	/** pStrResultDate_PROCEDURE_PARAMETER */
	public static final String pStrResultDate_PROCEDURE_PARAMETER = "out";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrDate;

	private BigDecimal pLeadTime;

	private String pStrCalCd;

	private String pStrResultDate;

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
	 * 入力日付取得
	 * @return String 入力日付
	 */
	public String getpStrDate() {
		return pStrDate;
	}

	/**
	 * 入力日付
	 * @param pStrDate 入力日付
	 */
	public void setpStrDate(String pStrDate) {
		this.pStrDate = pStrDate;
	}

	/**
	 * リードタイム取得
	 * @return String リードタイム
	 */
	public BigDecimal getpLeadTime() {
		return pLeadTime;
	}

	/**
	 * リードタイム
	 * @param pLeadTime リードタイム
	 */
	public void setpLeadTime(BigDecimal pLeadTime) {
		this.pLeadTime = pLeadTime;
	}

	/**
	 * カレンダーコード取得
	 * @return String カレンダーコード
	 */
	public String getpStrCalCd() {
		return pStrCalCd;
	}

	/**
	 * カレンダーコード
	 * @param pStrCalCd カレンダーコード
	 */
	public void setpStrCalCd(String pStrCalCd) {
		this.pStrCalCd = pStrCalCd;
	}

	/**
	 * 出力日付取得
	 * @return String 出力日付
	 */
	public String getpStrResultDate() {
		return pStrResultDate;
	}

	/**
	 * 出力日付
	 * @param pStrResultDate 出力日付
	 */
	public void setpStrResultDate(String pStrResultDate) {
		this.pStrResultDate = pStrResultDate;
	}

}
