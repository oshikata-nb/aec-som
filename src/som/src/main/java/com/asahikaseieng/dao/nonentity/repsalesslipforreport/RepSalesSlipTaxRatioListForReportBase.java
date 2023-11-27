/*
 * Created on 2009/07/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repsalesslipforreport;

import java.io.Serializable;

/**
 * RepSalesSlipDetailListForReportクラス.
 * @author kanri-user
 */
public class RepSalesSlipTaxRatioListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSalesSlipTaxRatioListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション salesSlipNo */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/** COLUMNアノテーション salesSlipNo */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション sumSalesAmount */
	public static final String sumSalesAmount_COLUMN = "SUM_SALES_AMOUNT";

	/** COLUMNアノテーション sumTaxAmount */
	public static final String sumTaxAmount_COLUMN = "SUM_TAX_AMOUNT";


	//
	// インスタンスフィールド
	//

	private String salesSlipNo;

	private String venderCd;

	private String taxRatio;

	private String sumSalesAmount;

	private String sumTaxAmount;

	//
	// インスタンスメソッド
	//

	/**
	 * salesSlipNo取得.
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return this.salesSlipNo;
	}

	/**
	 * salesSlipNo設定.
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	public String getVenderCd() {
		return venderCd;
	}

	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	public String getTaxRatio() {
		return taxRatio;
	}

	public void setTaxRatio(String taxRatio) {
		this.taxRatio = taxRatio;
	}

	public String getSumSalesAmount() {
		return sumSalesAmount;
	}

	public void setSumSalesAmount(String sumSalesAmount) {
		this.sumSalesAmount = sumSalesAmount;
	}

	public String getSumTaxAmount() {
		return sumTaxAmount;
	}

	public void setSumTaxAmount(String sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}
}

