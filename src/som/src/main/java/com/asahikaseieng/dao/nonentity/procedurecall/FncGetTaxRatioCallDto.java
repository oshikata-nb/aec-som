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
public final class FncGetTaxRatioCallDto {
	/** Constructor */
	public FncGetTaxRatioCallDto() {
	}

	/** pStrSlipNo_PROCEDURE_PARAMETER */
	public static final String pStrTaxRatio_PROCEDURE_PARAMETER = "return";

	/** pStrUpdatorCd_PROCEDURE_PARAMETER *//*
	public static final String pStrTargetDate_PROCEDURE_PARAMETER = "in";
*/
	//軽減税率対応
	/** pStrTaxCd_PROCEDURE_PARAMETER */
	public static final String pStrTaxCd_PROCEDURE_PARAMETER = "in";

	/** pStrParam1_PROCEDURE_PARAMETER */
	public static final String pStrParam1_PROCEDURE_PARAMETER = "in";

	/** pStrParam2_PROCEDURE_PARAMETER */
	public static final String pStrParam2_PROCEDURE_PARAMETER = "in";
	
	/** pStrParam3_PROCEDURE_PARAMETER */
	public static final String pStrParam3_PROCEDURE_PARAMETER = "in";
	
	/** pStrParam4_PROCEDURE_PARAMETER */
	public static final String pStrParam4_PROCEDURE_PARAMETER = "in";
	
	private String pStrTaxRatio;
	
	private String pStrTaxCd;
	
	private String pStrParam1;
	
	private String pStrParam2;
	
	private String pStrParam3;
	
	private String pStrParam4;

	/*private String pStrTargetDate;*/

	/**
	 * 消費税率取得
	 * @return String 消費税率
	 */
	public String getPStrTaxRatio() {
		return pStrTaxRatio;
	}

	/**
	 * 消費税率設定
	 * 
	 * @param strTaxRatio 消費税率
	 */
	public void setPStrTaxRatio(final String strTaxRatio) {
		pStrTaxRatio = strTaxRatio;
	}

	/**
	 * 消費税算出日
	 * @return String 消費税算出日
	 */
/*	public String getPStrTargetDate() {
		return pStrTargetDate;
	}*/

	/**
	 * 消費税算出日
	 * @param strTargetDate 消費税算出日
	 */
/*	public void setPStrTargetDate(final String strTargetDate) {
		pStrTargetDate = strTargetDate;
	}*/

	/**
	 * pStrParam1を取得します。
	 * @return pStrParam1
	 */
	public String getpStrParam1() {
		return pStrParam1;
	}

	/**
	 * pStrParam1を設定します。
	 * @param pStrParam1 pStrParam1
	 */
	public void setpStrParam1(String pStrParam1) {
		this.pStrParam1 = pStrParam1;
	}

	/**
	 * pStrParam2を取得します。
	 * @return pStrParam2
	 */
	public String getpStrParam2() {
		return pStrParam2;
	}

	/**
	 * pStrParam2を設定します。
	 * @param pStrParam2 pStrParam2
	 */
	public void setpStrParam2(String pStrParam2) {
		this.pStrParam2 = pStrParam2;
	}

	/**
	 * pStrParam3を取得します。
	 * @return pStrParam3
	 */
	public String getpStrParam3() {
		return pStrParam3;
	}

	/**
	 * pStrParam3を設定します。
	 * @param pStrParam3 pStrParam3
	 */
	public void setpStrParam3(String pStrParam3) {
		this.pStrParam3 = pStrParam3;
	}

	/**
	 * pStrParam4を取得します。
	 * @return pStrParam4
	 */
	public String getpStrParam4() {
		return pStrParam4;
	}

	/**
	 * pStrParam4を設定します。
	 * @param pStrParam4 pStrParam4
	 */
	public void setpStrParam4(String pStrParam4) {
		this.pStrParam4 = pStrParam4;
	}

	/**
	 * pStrTaxCdを取得します。
	 * @return pStrTaxCd
	 */
	public String getpStrTaxCd() {
		return pStrTaxCd;
	}

	/**
	 * pStrTaxCdを設定します。
	 * @param pStrTaxCd pStrTaxCd
	 */
	public void setpStrTaxCd(String pStrTaxCd) {
		this.pStrTaxCd = pStrTaxCd;
	}
}
