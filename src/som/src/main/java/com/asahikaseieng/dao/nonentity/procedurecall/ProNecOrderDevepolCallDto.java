/*
 * Created on 2008/07/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;


/**
 * 発注点発注展開処理用Dtoクラス
 * @author tosco
 */
public final class ProNecOrderDevepolCallDto {

	/** Constructor */
	public ProNecOrderDevepolCallDto() {
	}

	/** pStrRet_PROCEDURE_PARAMETER */
	public static final String pStrRet_PROCEDURE_PARAMETER = "out";

	private String pStrRet;

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
