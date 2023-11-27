/*
 * Created on 2008/07/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.sql.Timestamp;


/**
 * 購買計画確定用Dtoクラス
 * @author tosco
 */
public final class ProNecPurchaseDecideCallDto {

	/** Constructor */
	public ProNecPurchaseDecideCallDto() {
	}

	/** pDteOsDate_PROCEDURE_PARAMETER */
	public static final String pDteOsDate_PROCEDURE_PARAMETER = "in";

	/** pDteOeDate_PROCEDURE_PARAMETER */
	public static final String pDteOeDate_PROCEDURE_PARAMETER = "in";

	/** pDteDsDate_PROCEDURE_PARAMETER */
	public static final String pDteDsDate_PROCEDURE_PARAMETER = "in";

	/** pDteDeDate_PROCEDURE_PARAMETER */
	public static final String pDteDeDate_PROCEDURE_PARAMETER = "in";

	/** pStrVender_PROCEDURE_PARAMETER */
	public static final String pStrVender_PROCEDURE_PARAMETER = "in";

	/** pStrItem_PROCEDURE_PARAMETER */
	public static final String pStrItem_PROCEDURE_PARAMETER = "in";

	/** pStrRet_PROCEDURE_PARAMETER */
	public static final String pStrRet_PROCEDURE_PARAMETER = "out";

	/** 発注開始日 */
	private Timestamp pDteOsDate;
	/** 発注終了日 */
	private Timestamp pDteOeDate;
	/** 納期開始日 */
	private Timestamp pDteDsDate;
	/** 納期終了日 */
	private Timestamp pDteDeDate;
	/** 取引先コード */
	private String pStrVender;
	/** 品目コード */
	private String pStrItem;
	/** 処理結果メッセージ */
	private String pStrRet;

	/**
	 * pDteStartDateを取得します。
	 * @return pDteOsDate
	 */
	public Timestamp getPDteOsDate() {
		return pDteOsDate;
	}

	/**
	 * pDteStartDateを設定します。
	 * @param dteStartDate pDteOsDate
	 */
	public void setPDteOsDate(final Timestamp dteStartDate) {
		pDteOsDate = dteStartDate;
	}

	/**
	 * pDteEndDateを取得します。
	 * @return pDteOeDate
	 */
	public Timestamp getPDteOeDate() {
		return pDteOeDate;
	}

	/**
	 * pDteEndDateを設定します。
	 * @param dteEndDate pDteOeDate
	 */
	public void setPDteOeDate(final Timestamp dteEndDate) {
		pDteOeDate = dteEndDate;
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
	 * pDteDsDateを取得します。
	 * @return pDteDsDate
	 */
	public Timestamp getPDteDsDate() {
		return pDteDsDate;
	}

	/**
	 * pDteDsDateを設定します。
	 * @param dteDsDate pDteDsDate
	 */
	public void setPDteDsDate(final Timestamp dteDsDate) {
		pDteDsDate = dteDsDate;
	}

	/**
	 * pDteDeDateを取得します。
	 * @return pDteDeDate
	 */
	public Timestamp getPDteDeDate() {
		return pDteDeDate;
	}

	/**
	 * pDteDeDateを設定します。
	 * @param dteDeDate pDteDeDate
	 */
	public void setPDteDeDate(final Timestamp dteDeDate) {
		pDteDeDate = dteDeDate;
	}

	/**
	 * pStrVenderを取得します。
	 * @return pStrVender
	 */
	public String getPStrVender() {
		return pStrVender;
	}

	/**
	 * pStrVenderを設定します。
	 * @param strVender pStrVender
	 */
	public void setPStrVender(final String strVender) {
		pStrVender = strVender;
	}

	/**
	 * pStrItemを取得します。
	 * @return pStrItem
	 */
	public String getPStrItem() {
		return pStrItem;
	}

	/**
	 * pStrItemを設定します。
	 * @param strItem pStrItem
	 */
	public void setPStrItem(final String strItem) {
		pStrItem = strItem;
	}

}
