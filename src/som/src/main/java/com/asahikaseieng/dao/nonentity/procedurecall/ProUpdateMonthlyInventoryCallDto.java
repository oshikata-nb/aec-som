/*
 * Created on 2010/04/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 原価計算（材料・製品元帳ファイル）データ取込
 * @author t0011036
 */
public final class ProUpdateMonthlyInventoryCallDto {

	/** Constructor */
	public ProUpdateMonthlyInventoryCallDto() {
	}

	/** pNumTargetMonth_PROCEDURE_PARAMETER */
	public static final String pNumTargetMonth_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorReturnCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorReturnMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnMsg_PROCEDURE_PARAMETER = "out";

	/** pNumCnt_PROCEDURE_PARAMETER */
	public static final String pNumCnt_PROCEDURE_PARAMETER = "out";

	private BigDecimal pNumTargetMonth;

	private String pStrTantoCd;

	private String pStrErrorReturnCd;

	private String pStrErrorReturnMsg;

	private BigDecimal pNumCnt;

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
	 * pNumTargetMonthを取得します。
	 * @return pNumTargetMonth
	 */
	public BigDecimal getPNumTargetMonth() {
		return pNumTargetMonth;
	}

	/**
	 * pNumTargetMonthを設定します。
	 * @param numTargetMonth pNumTargetMonth
	 */
	public void setPNumTargetMonth(final BigDecimal numTargetMonth) {
		pNumTargetMonth = numTargetMonth;
	}

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
}
