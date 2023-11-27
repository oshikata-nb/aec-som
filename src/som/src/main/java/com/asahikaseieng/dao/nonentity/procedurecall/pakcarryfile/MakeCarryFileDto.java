/*
 * Created on 2008/01/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall.pakcarryfile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import oracle.sql.ARRAY;

/**
 * ##ここにクラスの説明を書いてください##
 * @author a1020630
 */
public final class MakeCarryFileDto {

	/** Constructor */
	public MakeCarryFileDto() {
	}

	/** pCarryCd_PROCEDURE_PARAMETER */
	public static final String pCarryCd_PROCEDURE_PARAMETER = "in";

	/** pUserCd_PROCEDURE_PARAMETER */
	public static final String pUserCd_PROCEDURE_PARAMETER = "in";

	/** pShippingNoList_PROCEDURE_PARAMETER */
	public static final String pShippingNoList_PROCEDURE_PARAMETER = "in";
	
	/** pStrErrorCd_PROCEDURE_PARAMETER */
	public static final String pStrErrorCd_PROCEDURE_PARAMETER = "out";

	/** pStrErrorMsg_PROCEDURE_PARAMETER */
	public static final String pStrErrorMsg_PROCEDURE_PARAMETER = "out";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pCarryCd;

	private String pUserCd;

	private String pShippingNoList;
	
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
	 * 運送会社コード取得
	 * @return String 運送会社コード
	 */
	public String getPCarryCd() {
		return pCarryCd;
	}

	/**
	 * 運送会社コード設定
	 * @param pCarryCd 出荷予定日
	 */
	public void setPCarryCd(final String pCarryCd) {
		this.pCarryCd = pCarryCd;
	}

	/**
	 * pUserCdを取得します。
	 * @return pUserCd
	 */
	public String getpUserCd() {
		return pUserCd;
	}

	/**
	 * pUserCdを設定します。
	 * @param pUserCd pUserCd
	 */
	public void setpUserCd(String pUserCd) {
		this.pUserCd = pUserCd;
	}

	/**
	 * pShippingNoListを取得します。
	 * @return pShippingNoList
	 */
	public String getpShippingNoList() {
		return pShippingNoList;
	}

	/**
	 * pShippingNoListを設定します。
	 * @param pShippingNoList pShippingNoList
	 */
	public void setpShippingNoList(String pShippingNoList) {
		this.pShippingNoList = pShippingNoList;
	}

}
