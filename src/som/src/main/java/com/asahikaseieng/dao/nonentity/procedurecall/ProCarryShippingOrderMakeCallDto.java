/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 運送店別出荷指図指図作成クラス
 * @author T1344224
 */
public final class ProCarryShippingOrderMakeCallDto {

	/** Constructor */
	public ProCarryShippingOrderMakeCallDto() {
	}

	/** pStrShippingDateFrom_PROCEDURE_PARAMETER */
	public static final String pStrShippingDate_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrShippingDate;

	private String pStrTantoCd;

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
	 * 出荷予定日取得
	 * @return String 出荷予定日
	 */
	public String getPStrShippingDate() {
		return pStrShippingDate;
	}

	/**
	 * 出荷予定日設定
	 * @param strShippingDate 出荷予定日
	 */
	public void setPStrShippingDate(final String strShippingDate) {
		pStrShippingDate = strShippingDate;
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
}
