/*
 * Created on 2008/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

import java.math.BigDecimal;


/**
 * 請求ロールバック処理用Dtoクラス
 * @author tosco
 */
public final class ProClRollbackCallDto {

	/** Constructor */
	public ProClRollbackCallDto() {
	}

	/** pStrOrganizationCd_PROCEDURE_PARAMETER */
	public static final String pStrOrganizationCd_PROCEDURE_PARAMETER = "in";

	/** pStrVenderCd_PROCEDURE_PARAMETER */
	public static final String pStrVenderCd_PROCEDURE_PARAMETER = "in";

	/** pStrCreditDate_PROCEDURE_PARAMETER */
	public static final String pStrCreditDate_PROCEDURE_PARAMETER = "in";

	/** pStrInputorCd_PROCEDURE_PARAMETER */
	public static final String pStrInputorCd_PROCEDURE_PARAMETER = "in";

	/** pNumRet_PROCEDURE_PARAMETER */
	public static final String pNumRet_PROCEDURE_PARAMETER = "out";

	/** pNumUpdateCount_PROCEDURE_PARAMETER */
	public static final String pNumUpdateCount_PROCEDURE_PARAMETER = "out";

	private String pStrOrganizationCd;

	private String pStrVenderCd;

	private String pStrCreditDate;

	private String pStrInputorCd;

	private BigDecimal pNumRet;

	private BigDecimal pNumUpdateCount;

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
	 * 請求先コード取得
	 * @return String 請求先コード
	 */
	public String getPStrVenderCd() {
		return pStrVenderCd;
	}

	/**
	 * 請求先コード設定
	 * @param strVenderCd 請求先コード
	 */
	public void setPStrVenderCd(final String strVenderCd) {
		pStrVenderCd = strVenderCd;
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

	/**
	 * 更新レコード件数取得

	 * @return BigDecimal 更新レコード件数
	 */
	public BigDecimal getPNumUpdateCount() {
		return pNumUpdateCount;
	}

	/**
	 * 更新レコード件数設定

	 * @param pNumUpdateCount 更新レコード件数
	 */
	public void setPNumUpdateCount(final BigDecimal pNumUpdateCount) {
		this.pNumUpdateCount = pNumUpdateCount;
	}

}
