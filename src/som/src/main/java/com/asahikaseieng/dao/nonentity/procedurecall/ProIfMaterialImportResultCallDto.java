/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;

/**
 * 購買の受入入力Dtoクラス
 * @author T1344224
 */
public final class ProIfMaterialImportResultCallDto {

	/** Constructor */
	public ProIfMaterialImportResultCallDto() {
	}

	/** pStrShippingDateFrom_PROCEDURE_PARAMETER */
	public static final String pStrLotNo_PROCEDURE_PARAMETER = "in";

	/** pStrShippingDateFrom_PROCEDURE_PARAMETER */
	public static final String pNumQty_PROCEDURE_PARAMETER = "in";

	/** pStrLocation_PROCEDURE_PARAMETER */
	public static final String pStrLocation_PROCEDURE_PARAMETER = "in";

	/** pNumOrderDivision_PROCEDURE_PARAMETER */
	public static final String pNumOrderDivision_PROCEDURE_PARAMETER = "in";

	/** pStrItemCd_PROCEDURE_PARAMETER */
	public static final String pStrItemCd_PROCEDURE_PARAMETER = "in";

	/** pStrTantoCd_PROCEDURE_PARAMETER */
	public static final String pStrTantoCd_PROCEDURE_PARAMETER = "in";

	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pStrReturnCd_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrLotNo;

	private BigDecimal pNumQty;

	private String pStrLocation;

	private BigDecimal pNumOrderDivision;

	private String pStrItemCd;

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
	 * 包装指図番号取得
	 * @return String 出荷予定日(From)
	 */
	public String getPStrLotNo() {
		return pStrLotNo;
	}

	/**
	 * 包装指図番号設定
	 * @param strShippingDateFrom 包装指図番号
	 */
	public void setPStrLotNo(final String strShippingDateFrom) {
		pStrLotNo = strShippingDateFrom;
	}

	/**
	 * 物流入庫実績数取得
	 * @return String 物流入庫実績数
	 */
	public BigDecimal getPNumQty() {
		return pNumQty;
	}

	/**
	 * 物流入庫実績数設定
	 * @param strShippingDateTo 物流入庫実績数
	 */
	public void setPNumQty(final BigDecimal strShippingDateTo) {
		pNumQty = strShippingDateTo;
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
	 * 担当コード取得
	 * @return String 担当コード
	 */
	public BigDecimal getPNumOrderDivision() {
		return pNumOrderDivision;
	}

	/**
	 * 担当コード設定
	 * @param numOrderDivision 担当コード
	 */
	public void setPNumOrderDivision(final BigDecimal numOrderDivision) {
		pNumOrderDivision = numOrderDivision;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getPStrItemCd() {
		return pStrItemCd;
	}

	/**
	 * 品目コード設定
	 * @param strItemCd 品目コード
	 */
	public void setPStrItemCd(final String strItemCd) {
		pStrItemCd = strItemCd;
	}

	/**
	 * ロケーション取得
	 * @return String ロケーション
	 */
	public String getPStrLocation() {
		return pStrLocation;
	}

	/**
	 * ロケーション設定
	 * @param strLoctation ロケーション
	 */
	public void setPStrLocation(final String strLoctation) {
		pStrLocation = strLoctation;
	}
}
