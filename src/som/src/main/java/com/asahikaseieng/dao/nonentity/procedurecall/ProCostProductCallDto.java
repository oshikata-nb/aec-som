/*
 * Created on 2008/07/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.sql.Timestamp;


/**
 * 原価積上処理用Dtoクラス
 * @author tosco
 */
public final class ProCostProductCallDto {

	/** Constructor */
	public ProCostProductCallDto() {
	}

	/** pDteRecipeDate_PROCEDURE_PARAMETER */
	public static final String pDteRecipeDate_PROCEDURE_PARAMETER = "in";

	/** pStrRet_PROCEDURE_PARAMETER */
	public static final String pStrRet_PROCEDURE_PARAMETER = "out";

	private Timestamp pDteRecipeDate;

	private String pStrRet;

	/**
	 * pDteStartDateを取得します。
	 * @return pDteRecipeDate
	 */
	public Timestamp getPDteRecipeDate() {
		return pDteRecipeDate;
	}

	/**
	 * pDteStartDateを設定します。
	 * @param dteRecipeDate dteRecipeDate
	 */
	public void setPDteRecipeDate(final Timestamp dteRecipeDate) {
		pDteRecipeDate = dteRecipeDate;
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

}
