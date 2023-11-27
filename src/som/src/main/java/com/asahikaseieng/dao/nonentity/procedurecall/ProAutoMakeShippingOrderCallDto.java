/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 出荷指図自動作成用Dtoクラス
 * @author T1344224
 */
public final class ProAutoMakeShippingOrderCallDto {

	/** Constructor */
	public ProAutoMakeShippingOrderCallDto() {
	}

	/** pStrOrderNo_PROCEDURE_PARAMETER */
	public static final String pStrOrderNo_PROCEDURE_PARAMETER = "in";

	/** pNumOrderRowNo_PROCEDURE_PARAMETER */
	public static final String pNumOrderRowNo_PROCEDURE_PARAMETER = "in";

	/** pStrShippingDateFrom_PROCEDURE_PARAMETER */
	public static final String pStrShippingDateFrom_PROCEDURE_PARAMETER = "in";

	/** pStrShippingDateFrom_PROCEDURE_PARAMETER */
	public static final String pStrShippingDateTo_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrOrderNo;

	private BigDecimal pNumOrderRowNo;

	private String pStrShippingDateFrom;

	private String pStrShippingDateTo;

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
	 * 出荷予定日(From)取得
	 * @return String 出荷予定日(From)
	 */
	public String getPStrShippingDateFrom() {
		return pStrShippingDateFrom;
	}

	/**
	 * 出荷予定日(From)設定
	 * @param strShippingDateFrom 出荷予定日(From)
	 */
	public void setPStrShippingDateFrom(final String strShippingDateFrom) {
		pStrShippingDateFrom = strShippingDateFrom;
	}

	/**
	 * 出荷予定日(To)取得
	 * @return String 出荷予定日(To)
	 */
	public String getPStrShippingDateTo() {
		return pStrShippingDateTo;
	}

	/**
	 * 出荷予定日(To)設定
	 * @param strShippingDateTo 出荷予定日(To)
	 */
	public void setPStrShippingDateTo(final String strShippingDateTo) {
		pStrShippingDateTo = strShippingDateTo;
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

	/**
	 * 受注行番号取得
	 * @return BigDecimal 受注行番号
	 */
	public BigDecimal getPNumOrderRowNo() {
		return pNumOrderRowNo;
	}

	/**
	 * 受注行番号設定
	 * @param numOrderRowNo 受注行番号
	 */
	public void setPNumOrderRowNo(final BigDecimal numOrderRowNo) {
		pNumOrderRowNo = numOrderRowNo;
	}

	/**
	 * 受注番号取得
	 * @return String 受注番号
	 */
	public String getPStrOrderNo() {
		return pStrOrderNo;
	}

	/**
	 * 受注行番号設定
	 * @param strOrderNo 受注行番号
	 */
	public void setPStrOrderNo(final String strOrderNo) {
		pStrOrderNo = strOrderNo;
	}
}
