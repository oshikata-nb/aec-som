/*
 * Created on 2008/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;


/**
 * 買掛更新処理用Dtoクラス
 * @author tosco
 */
public final class ProApUpdateCallDto {

	/** Constructor */
	public ProApUpdateCallDto() {
	}

	/** pStrOrganizationCd_PROCEDURE_PARAMETER */
	public static final String pStrOrganizationCd_PROCEDURE_PARAMETER = "in";

	/** pStrPayableDate_PROCEDURE_PARAMETER */
	public static final String pStrPayableDate_PROCEDURE_PARAMETER = "in";

	/** pStrTempClosingFlg_PROCEDURE_PARAMETER */
	public static final String pStrTempClosingFlg_PROCEDURE_PARAMETER = "in";

	/** pStrInputorCd_PROCEDURE_PARAMETER */
	public static final String pStrInputorCd_PROCEDURE_PARAMETER = "in";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrOrganizationCd;

	private String pStrPayableDate;

	private String pStrTempClosingFlg;

	private String pStrInputorCd;

	private BigDecimal pNumRet;

	/**
	 * 部署コード取得
	 * @return String 部署コード
	 */
	public String getPStrOrganizationCd() {
		return pStrOrganizationCd;
	}

	/**
	 * 部署コード設定
	 * @param strOrganizationCd 部署コード
	 */
	public void setPStrOrganizationCd(final String strOrganizationCd) {
		pStrOrganizationCd = strOrganizationCd;
	}

	/**
	 * 買掛締め日取得
	 * @return String 買掛締め日
	 */
	public String getPStrPayableDate() {
		return pStrPayableDate;
	}

	/**
	 * 買掛締め日設定
	 * @param strPayableDate 買掛締め日
	 */
	public void setPStrPayableDate(final String strPayableDate) {
		pStrPayableDate = strPayableDate;
	}

	/**
	 * 仮締区分取得
	 * @return String 仮締区分
	 */
	public String getPStrTempClosingFlg() {
		return pStrTempClosingFlg;
	}

	/**
	 * 仮締区分設定
	 * @param strTempClosingFlg 仮締区分
	 */
	public void setPStrTempClosingFlg(final String strTempClosingFlg) {
		pStrTempClosingFlg = strTempClosingFlg;
	}

	/**
	 * 登録者ＩＤ取得
	 * @return String 登録者ＩＤ
	 */
	public String getPStrInputorCd() {
		return pStrInputorCd;
	}

	/**
	 * 登録者ＩＤ設定
	 * @param strInputorCd 登録者ＩＤ
	 */
	public void setPStrInputorCd(final String strInputorCd) {
		pStrInputorCd = strInputorCd;
	}

	/**
	 * 戻り値取得
	 * @return BigDecimal 戻り値
	 */
	public BigDecimal getPNumRet() {
		return pNumRet;
	}

	/**
	 * 戻り値設定
	 * @param numRet 戻り値
	 */
	public void setPNumRetPara(final BigDecimal numRet) {
		this.pNumRet = numRet;
	}

}
