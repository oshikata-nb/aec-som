/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.sql.Timestamp;

/**
 * 標準販売単価更新処理
 * @author T1344224
 */
public final class UpdateItemPriceCallDto {

	/** Constructor */
	public UpdateItemPriceCallDto() {
	}

	/** pDatStandardDate_PROCEDURE_PARAMETER */
	public static final String pDatStandardDate_PROCEDURE_PARAMETER = "in";

	/** pStrItemCd_PROCEDURE_PARAMETER */
	public static final String pStrItemCd_PROCEDURE_PARAMETER = "in";

	/** pStrPrice_PROCEDURE_PARAMETER */
	public static final String pStrPrice_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorReturnCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorReturnMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorReturnMsg_PROCEDURE_PARAMETER = "out";

	/** pStrUpdateCnt_PROCEDURE_PARAMETER */
	public static final String pStrUpdateCnt_PROCEDURE_PARAMETER = "out";

	private Timestamp pDatStandardDate;

	private String pStrItemCd;

	private String pStrPrice;

	private String pStrTantoCd;

	private String pStrErrorReturnCd;

	private String pStrErrorReturnMsg;

	private String pStrUpdateCnt;

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
	 * pStrItemCdを取得します。
	 * @return pStrItemCd
	 */
	public String getPStrItemCd() {
		return pStrItemCd;
	}

	/**
	 * pStrItemCdを設定します。
	 * @param strItemCd pStrItemCd
	 */
	public void setPStrItemCd(final String strItemCd) {
		pStrItemCd = strItemCd;
	}

	/**
	 * pStrPriceを取得します。
	 * @return pStrPrice
	 */
	public String getPStrPrice() {
		return pStrPrice;
	}

	/**
	 * pStrPriceを設定します。
	 * @param strPrice pStrPrice
	 */
	public void setPStrPrice(final String strPrice) {
		pStrPrice = strPrice;
	}

	/**
	 * pStrUpdateCntを取得します。
	 * @return pStrUpdateCnt
	 */
	public String getPStrUpdateCnt() {
		return pStrUpdateCnt;
	}

	/**
	 * pStrUpdateCntを設定します。
	 * @param strUpdateCnt pStrUpdateCnt
	 */
	public void setPStrUpdateCnt(final String strUpdateCnt) {
		pStrUpdateCnt = strUpdateCnt;
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
	 * pDatStandardDateを取得します。
	 * @return pDatStandardDate
	 */
	public Timestamp getPDatStandardDate() {
		return pDatStandardDate;
	}

	/**
	 * pDatStandardDateを設定します。
	 * @param datStandardDate pDatStandardDate
	 */
	public void setPDatStandardDate(final Timestamp datStandardDate) {
		pDatStandardDate = datStandardDate;
	}
}
