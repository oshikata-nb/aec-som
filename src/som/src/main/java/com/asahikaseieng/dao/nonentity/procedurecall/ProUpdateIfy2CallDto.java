/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 原価計算（品目マスタ）データ送信
 * @author t0011036
 */
public final class ProUpdateIfy2CallDto {

	/** Constructor */
	public ProUpdateIfy2CallDto() {
	}

	/** pStrTargetMonth_PROCEDURE_PARAMETER */
	public static final String pStrTargetMonth_PROCEDURE_PARAMETER = "in";

	/** pNumSendDivision_PROCEDURE_PARAMETER */
	public static final String pNumSendDivision_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pNumCnt_PROCEDURE_PARAMETER */
	public static final String pNumCnt_PROCEDURE_PARAMETER = "out";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrTargetMonth;

	private BigDecimal pNumSendDivision;

	private String pStrTantoCd;

	private BigDecimal pNumCnt;

	private BigDecimal pNumRet;

	/**
	 * pStrTargetMonthを取得します。
	 * @return pStrTargetMonth
	 */
	public String getPStrTargetMonth() {
		return pStrTargetMonth;
	}

	/**
	 * pStrTargetMonthを設定します。
	 * @param strTargetMonth pStrTargetMonth
	 */
	public void setPStrTargetMonth(final String strTargetMonth) {
		pStrTargetMonth = strTargetMonth;
	}

	/**
	 * pNumCntを取得します。
	 * @return pNumCnt
	 */
	public BigDecimal getPNumCnt() {
		return pNumCnt;
	}

	/**
	 * pNumCntを設定します。
	 * @param numCnt pNumCnt
	 */
	public void setPNumCnt(final BigDecimal numCnt) {
		pNumCnt = numCnt;
	}

	/**
	 * pNumRetを取得します。
	 * @return pNumRet
	 */
	public BigDecimal getPNumRet() {
		return pNumRet;
	}

	/**
	 * pNumRetを設定します。
	 * @param numRet pNumRet
	 */
	public void setPNumRet(final BigDecimal numRet) {
		pNumRet = numRet;
	}

	/**
	 * pStrTantoCdを取得します。
	 * @return pStrTantoCd
	 */
	public String getPStrTantoCd() {
		return pStrTantoCd;
	}

	/**
	 * pStrTantoCdを設定します。
	 * @param strTantoCd pStrTantoCd
	 */
	public void setPStrTantoCd(final String strTantoCd) {
		pStrTantoCd = strTantoCd;
	}

	/**
	 * pNumSendDivisionを取得します。
	 * @return pNumSendDivision
	 */
	public BigDecimal getPNumSendDivision() {
		return pNumSendDivision;
	}

	/**
	 * pNumSendDivisionを設定します。
	 * @param numSendDivision pNumSendDivision
	 */
	public void setPNumSendDivision(final BigDecimal numSendDivision) {
		pNumSendDivision = numSendDivision;
	}
}
