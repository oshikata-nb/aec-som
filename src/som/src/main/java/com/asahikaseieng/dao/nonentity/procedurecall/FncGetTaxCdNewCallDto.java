/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.procedurecall;

/**
 * 発番番号(売掛、請求、買掛、支払、相殺、消込、入金番号)取得用Dtoクラス
 * @author tosco
 */
public final class FncGetTaxCdNewCallDto {
	/** Constructor */
	public FncGetTaxCdNewCallDto() {
	}
	//軽減税率対応
	/** pStrSlipNo_PROCEDURE_PARAMETER */
	public static final String pTaxCd_PROCEDURE_PARAMETER = "return";

	/** pParamDate_PROCEDURE_PARAMETER */
	public static final String pParamDate_PROCEDURE_PARAMETER = "in";

	/** pParamItemCd_PROCEDURE_PARAMETER */
	public static final String pParamItemCd_PROCEDURE_PARAMETER = "in";

	/** pParamVenderDivision_PROCEDURE_PARAMETER */
	public static final String pParamVenderDivision_PROCEDURE_PARAMETER = "in";

	/** pParamVenderCd_PROCEDURE_PARAMETER */
	public static final String pParamVenderCd_PROCEDURE_PARAMETER = "in";

	/** pParamCategory_PROCEDURE_PARAMETER */
	public static final String pParamCategory_PROCEDURE_PARAMETER = "in";

	/** pParamTaxRatio_PROCEDURE_PARAMETER */
	public static final String pParamTaxRatio_PROCEDURE_PARAMETER = "in";

	/** pParam1_PROCEDURE_PARAMETER */
	public static final String pParam1_PROCEDURE_PARAMETER = "in";

	/** pParam2_PROCEDURE_PARAMETER */
	public static final String pParam2_PROCEDURE_PARAMETER = "in";

	/** pParam3_PROCEDURE_PARAMETER */
	public static final String pParam3_PROCEDURE_PARAMETER = "in";

	/** pParamTaxFreeFlg_PROCEDURE_PARAMETER */
	public static final String pParamTaxFreeFlg_PROCEDURE_PARAMETER = "in";

	private String pTaxCd;

	private String pParamDate;

	private String pParamItemCd;

	private String pParamVenderDivision;

	private String pParamVenderCd;

	private String pParamCategory;

	private String pParamTaxRatio;

	private String pParam1;

	private String pParam2;

	private String pParam3;

	private String pParamTaxFreeFlg;

	/**
	 * pParamDateを取得します。
	 * @return pParamDate
	 */
	public String getpParamDate() {
		return pParamDate;
	}

	/**
	 * pParamDateを設定します。
	 * @param pParamDate pParamDate
	 */
	public void setpParamDate(String pParamDate) {
		this.pParamDate = pParamDate;
	}

	/**
	 * pParamItemCdを取得します。
	 * @return pParamItemCd
	 */
	public String getpParamItemCd() {
		return pParamItemCd;
	}

	/**
	 * pParamItemCdを設定します。
	 * @param pParamItemCd pParamItemCd
	 */
	public void setpParamItemCd(String pParamItemCd) {
		this.pParamItemCd = pParamItemCd;
	}

	/**
	 * pParamVenderDivisionを取得します。
	 * @return pParamVenderDivision
	 */
	public String getpParamVenderDivision() {
		return pParamVenderDivision;
	}

	/**
	 * pParamVenderDivisionを設定します。
	 * @param pParamVenderDivision pParamVenderDivision
	 */
	public void setpParamVenderDivision(String pParamVenderDivision) {
		this.pParamVenderDivision = pParamVenderDivision;
	}

	/**
	 * pParamVenderCdを取得します。
	 * @return pParamVenderCd
	 */
	public String getpParamVenderCd() {
		return pParamVenderCd;
	}

	/**
	 * pParamVenderCdを設定します。
	 * @param pParamVenderCd pParamVenderCd
	 */
	public void setpParamVenderCd(String pParamVenderCd) {
		this.pParamVenderCd = pParamVenderCd;
	}

	/**
	 * pParamCategoryを取得します。
	 * @return pParamCategory
	 */
	public String getpParamCategory() {
		return pParamCategory;
	}

	/**
	 * pParamCategoryを設定します。
	 * @param pParamCategory pParamCategory
	 */
	public void setpParamCategory(String pParamCategory) {
		this.pParamCategory = pParamCategory;
	}

	/**
	 * pParamTaxRatioを取得します。
	 * @return pParamTaxRatio
	 */
	public String getpParamTaxRatio() {
		return pParamTaxRatio;
	}

	/**
	 * pParamTaxRatioを設定します。
	 * @param pParamTaxRatio pParamTaxRatio
	 */
	public void setpParamTaxRatio(String pParamTaxRatio) {
		this.pParamTaxRatio = pParamTaxRatio;
	}

	/**
	 * pParam1を取得します。
	 * @return pParam1
	 */
	public String getpParam1() {
		return pParam1;
	}

	/**
	 * pParam1を設定します。
	 * @param pParam1 pParam1
	 */
	public void setpParam1(String pParam1) {
		this.pParam1 = pParam1;
	}

	/**
	 * pParam2を取得します。
	 * @return pParam2
	 */
	public String getpParam2() {
		return pParam2;
	}

	/**
	 * pParam2を設定します。
	 * @param pParam2 pParam2
	 */
	public void setpParam2(String pParam2) {
		this.pParam2 = pParam2;
	}

	/**
	 * pParam3を取得します。
	 * @return pParam3
	 */
	public String getpParam3() {
		return pParam3;
	}

	/**
	 * pParam3を設定します。
	 * @param pParam3 pParam3
	 */
	public void setpParam3(String pParam3) {
		this.pParam3 = pParam3;
	}

	/**
	 * pTaxCdを取得します。
	 * @return pTaxCd
	 */
	public String getpTaxCd() {
		return pTaxCd;
	}

	/**
	 * pTaxCdを設定します。
	 * @param pTaxCd pTaxCd
	 */
	public void setpTaxCd(String pTaxCd) {
		this.pTaxCd = pTaxCd;
	}

	/**
	 * pParamTaxFreeFlgを取得します。
	 * @return pParamTaxFreeFlg
	 */
	public String getpParamTaxFreeFlg() {
		return pParamTaxFreeFlg;
	}

	/**
	 * pParamTaxFreeFlgを設定します。
	 * @param pParamTaxFreeFlg pParamTaxFreeFlg
	 */
	public void setpParamTaxFreeFlg(String pParamTaxFreeFlg) {
		this.pParamTaxFreeFlg = pParamTaxFreeFlg;
	}
}
