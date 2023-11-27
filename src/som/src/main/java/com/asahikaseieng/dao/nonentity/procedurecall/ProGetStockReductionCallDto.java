/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 仕入割引額取得
 * @author T1344224
 */
public final class ProGetStockReductionCallDto {

	/** Constructor */
	public ProGetStockReductionCallDto() {
	}

	/** pStrVenderCd_PROCEDURE_PARAMETER */
	public static final String pStrVenderCd_PROCEDURE_PARAMETER = "in";

	/** pNumPayableAmount_PROCEDURE_PARAMETER */
	public static final String pNumPayableAmount_PROCEDURE_PARAMETER = "in";

	/** pStrCreditDivision_PROCEDURE_PARAMETER */
	public static final String pStrCreditDivision_PROCEDURE_PARAMETER = "out";

	/** pNumNoteSight_PROCEDURE_PARAMETER */
	public static final String pNumNoteSight_PROCEDURE_PARAMETER = "out";

	/** pNumDiscountDays_PROCEDURE_PARAMETER */
	public static final String pNumDiscountDays_PROCEDURE_PARAMETER = "out";

	/** pNumStockReduction_PROCEDURE_PARAMETER */
	public static final String pNumStockReduction_PROCEDURE_PARAMETER = "out";

	/** pNumCreditMonthDivision_PROCEDURE_PARAMETER */
	public static final String pNumCreditMonthDivision_PROCEDURE_PARAMETER = "out";

	/** pStrErrorReturnCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorReturnMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnMsg_PROCEDURE_PARAMETER = "out";

	private String pStrVenderCd;

	private BigDecimal pNumPayableAmount;

	private String pStrCreditDivision;

	private BigDecimal pNumNoteSight;

	private BigDecimal pNumDiscountDays;

	private BigDecimal pNumStockReduction;

	private BigDecimal pNumCreditMonthDivision;

	private String pStrErrorReturnCd;

	private String pStrErrorReturnMsg;

	/**
	 * pStrErrorReturnCdを取得します。
	 * @return pStrErrorReturnCd
	 */
	public String getPStrErrorReturnCd() {
		return pStrErrorReturnCd;
	}

	/**
	 * pStrErrorReturnCdを設定します。
	 * @param strErrorReturnCd pStrErrorReturnCd
	 */
	public void setPStrErrorReturnCd(final String strErrorReturnCd) {
		pStrErrorReturnCd = strErrorReturnCd;
	}

	/**
	 * pStrErrorReturnMsgを取得します。
	 * @return pStrErrorReturnMsg
	 */
	public String getPStrErrorReturnMsg() {
		return pStrErrorReturnMsg;
	}

	/**
	 * pStrErrorReturnMsgを設定します。
	 * @param strErrorReturnMsg pStrErrorReturnMsg
	 */
	public void setPStrErrorReturnMsg(final String strErrorReturnMsg) {
		pStrErrorReturnMsg = strErrorReturnMsg;
	}

	/**
	 * pNumDiscountDaysを取得します。
	 * @return pNumDiscountDays
	 */
	public BigDecimal getPNumDiscountDays() {
		return pNumDiscountDays;
	}

	/**
	 * pNumDiscountDaysを設定します。
	 * @param numDiscountDays pNumDiscountDays
	 */
	public void setPNumDiscountDays(final BigDecimal numDiscountDays) {
		pNumDiscountDays = numDiscountDays;
	}

	/**
	 * pNumNoteSightを取得します。
	 * @return pNumNoteSight
	 */
	public BigDecimal getPNumNoteSight() {
		return pNumNoteSight;
	}

	/**
	 * pNumNoteSightを設定します。
	 * @param numNoteSight pNumNoteSight
	 */
	public void setPNumNoteSight(final BigDecimal numNoteSight) {
		pNumNoteSight = numNoteSight;
	}

	/**
	 * pNumPayableAmountを取得します。
	 * @return pNumPayableAmount
	 */
	public BigDecimal getPNumPayableAmount() {
		return pNumPayableAmount;
	}

	/**
	 * pNumPayableAmountを設定します。
	 * @param numPayableAmount pNumPayableAmount
	 */
	public void setPNumPayableAmount(final BigDecimal numPayableAmount) {
		pNumPayableAmount = numPayableAmount;
	}

	/**
	 * pNumStockReductionを取得します。
	 * @return pNumStockReduction
	 */
	public BigDecimal getPNumStockReduction() {
		return pNumStockReduction;
	}

	/**
	 * pNumStockReductionを設定します。
	 * @param numStockReduction pNumStockReduction
	 */
	public void setPNumStockReduction(final BigDecimal numStockReduction) {
		pNumStockReduction = numStockReduction;
	}

	/**
	 * pStrCreditDivisionを取得します。
	 * @return pStrCreditDivision
	 */
	public String getPStrCreditDivision() {
		return pStrCreditDivision;
	}

	/**
	 * pStrCreditDivisionを設定します。
	 * @param strCreditDivision pStrCreditDivision
	 */
	public void setPStrCreditDivision(final String strCreditDivision) {
		pStrCreditDivision = strCreditDivision;
	}

	/**
	 * pStrVenderCdを取得します。
	 * @return pStrVenderCd
	 */
	public String getPStrVenderCd() {
		return pStrVenderCd;
	}

	/**
	 * pStrVenderCdを設定します。
	 * @param strVenderCd pStrVenderCd
	 */
	public void setPStrVenderCd(final String strVenderCd) {
		pStrVenderCd = strVenderCd;
	}

	/**
	 * pNumCreditMonthDivisionを取得します。
	 * @return pNumCreditMonthDivision
	 */
	public BigDecimal getPNumCreditMonthDivision() {
		return pNumCreditMonthDivision;
	}

	/**
	 * pNumCreditMonthDivisionを設定します。
	 * @param numCreditMonthDivision pNumCreditMonthDivision
	 */
	public void setPNumCreditMonthDivision(
			final BigDecimal numCreditMonthDivision) {
		pNumCreditMonthDivision = numCreditMonthDivision;
	}
}
