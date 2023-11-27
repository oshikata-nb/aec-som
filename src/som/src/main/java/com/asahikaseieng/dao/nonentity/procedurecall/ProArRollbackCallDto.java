/*
 * Created on 2008/07/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;


/**
 * 売掛ロールバック処理用Dtoクラス
 * @author tosco
 */
public final class ProArRollbackCallDto {

	/** Constructor */
	public ProArRollbackCallDto() {
	}

	/** pStrOrganizationCd_PROCEDURE_PARAMETER */
	public static final String pStrOrganizationCd_PROCEDURE_PARAMETER = "in";

	/** pStrCreditDate_PROCEDURE_PARAMETER */
	public static final String pStrCreditDate_PROCEDURE_PARAMETER = "in";

	/** pStrInputorCd_PROCEDURE_PARAMETER */
	public static final String pStrInputorCd_PROCEDURE_PARAMETER = "in";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	private String pStrOrganizationCd;

	private String pStrCreditDate;

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
	 * 売掛締め日取得
	 * @return String 売掛締め日
	 */
	public String getPStrCreditDate() {
		return pStrCreditDate;
	}

	/**
	 * 売掛締め日設定
	 * @param strCreditDate 売掛締め日
	 */
	public void setPStrCreditDate(final String strCreditDate) {
		pStrCreditDate = strCreditDate;
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
