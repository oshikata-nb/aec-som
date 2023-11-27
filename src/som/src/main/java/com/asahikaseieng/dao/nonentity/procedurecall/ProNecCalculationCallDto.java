/*
 * Created on 2008/07/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 原材料所要量計算用Dtoクラス
 * @author tosco
 */
public final class ProNecCalculationCallDto {

	/** Constructor */
	public ProNecCalculationCallDto() {
	}

	/** pDteStartDate_PROCEDURE_PARAMETER */
	public static final String pDteStartDate_PROCEDURE_PARAMETER = "in";

	/** pDteEndDate_PROCEDURE_PARAMETER */
	public static final String pDteEndDate_PROCEDURE_PARAMETER = "in";

	/** pNumLeadTime_PROCEDURE_PARAMETER */
	public static final String pNumLeadTime_PROCEDURE_PARAMETER = "in";

	/** pNumSafetyInventry_PROCEDURE_PARAMETER */
	public static final String pNumSafetyInventry_PROCEDURE_PARAMETER = "in";

	/** pStrRet_PROCEDURE_PARAMETER */
	public static final String pStrRet_PROCEDURE_PARAMETER = "out";

	private Timestamp pDteStartDate;

	private Timestamp pDteEndDate;

	private BigDecimal pNumLeadTime;

	private BigDecimal pNumSafetyInventry;

	private String pStrRet;

	/**
	 * pDteEndDateを取得します。
	 * @return pDteEndDate
	 */
	public Timestamp getPDteEndDate() {
		return pDteEndDate;
	}

	/**
	 * pDteEndDateを設定します。
	 * @param dteEndDate pDteEndDate
	 */
	public void setPDteEndDate(final Timestamp dteEndDate) {
		pDteEndDate = dteEndDate;
	}

	/**
	 * pDteStartDateを取得します。
	 * @return pDteStartDate
	 */
	public Timestamp getPDteStartDate() {
		return pDteStartDate;
	}

	/**
	 * pDteStartDateを設定します。
	 * @param dteStartDate pDteStartDate
	 */
	public void setPDteStartDate(final Timestamp dteStartDate) {
		pDteStartDate = dteStartDate;
	}

	/**
	 * pNumLeadTimeを取得します。
	 * @return pNumLeadTime
	 */
	public BigDecimal getPNumLeadTime() {
		return pNumLeadTime;
	}

	/**
	 * pNumLeadTimeを設定します。
	 * @param numLeadTime pNumLeadTime
	 */
	public void setPNumLeadTime(final BigDecimal numLeadTime) {
		pNumLeadTime = numLeadTime;
	}

	/**
	 * pStrRetを取得します。
	 * @return pStrRet
	 */
	public String getPStrRet() {
		return pStrRet;
	}

	/**
	 * pStrRetを設定します。
	 * @param strRet strRet
	 */
	public void setPStrRet(final String strRet) {
		pStrRet = strRet;
	}

	/**
	 * pNumSafetyInventryを取得します。
	 * @return pNumSafetyInventry
	 */
	public BigDecimal getPNumSafetyInventry() {
		return pNumSafetyInventry;
	}

	/**
	 * pNumSafetyInventryを設定します。
	 * @param numSafetyInventry pNumSafetyInventry
	 */
	public void setPNumSafetyInventry(final BigDecimal numSafetyInventry) {
		pNumSafetyInventry = numSafetyInventry;
	}

}
