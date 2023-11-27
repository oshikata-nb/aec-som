/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buying;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 仕入一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class BuyingListBase implements Serializable {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション stockingDate */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	// /** COLUMNアノテーション housingLocationCd */
	// public static final String housingLocationCd_COLUMN =
	// "HOUSING_LOCATION_CD";

	/** COLUMNアノテーション chargeOrganizationCd */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション remark2 */
	public static final String remark2_COLUMN = "REMARK2";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション payableUpdateDivision */
	public static final String payableUpdateDivision_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** COLUMNアノテーション paymentUpdateDivision */
	public static final String paymentUpdateDivision_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** COLUMNアノテーション slipIssueDivision */
	public static final String slipIssueDivision_COLUMN = "SLIP_ISSUE_DIVISION";

	/** COLUMNアノテーション taxCd */
	public static final String taxCd_COLUMN = "TAX_CD";

	//
	// インスタンスフィールド
	//

	/** 分類コード */
	private BigDecimal categoryDivision;

	/** 仕入日 */
	private Timestamp stockingDate;

	/** 仕入番号 */
	private String slipNo;

	/** ロット番号 */
	private String lotNo;

	/** 仕入数量 */
	private BigDecimal stockingQuantity;

	/** 単価 */
	private BigDecimal housingUnitprice;

	/** 金額 */
	private BigDecimal stockingAmount;

	/** 受入日 */
	private Timestamp acceptDate;

	/** 購買番号 */
	private String purchaseNo;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 部門コード */
	private String sectionCd;

	/** 担当者コード */
	private String tantoCd;

	// /** ロケーションコード */
	// private String housingLocationCd;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 適用(備考) */
	private String remark2;

	/** 仕入ステータス */
	private String status2;

	/** 買掛更新フラグ(0：未処理、1：処理済) */
	private String payableUpdateDivision;

	/** 支払更新フラグ(0：未処理、1：処理済) */
	private String paymentUpdateDivision;

	/** 伝票発行済区分 */
	private BigDecimal slipIssueDivision;
	
	//軽減税率対応
	/** 消費税コード */
	private String taxCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 分類コード取得
	 * @return BigDecimal 分類コード
	 */
	public BigDecimal getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final BigDecimal categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 仕入日取得
	 * @return Timestamp 仕入日
	 */
	public Timestamp getStockingDate() {
		return this.stockingDate;
	}

	/**
	 * 仕入日設定
	 * @param stockingDate 仕入日
	 */
	public void setStockingDate(final Timestamp stockingDate) {
		this.stockingDate = stockingDate;
	}

	/**
	 * 仕入番号取得
	 * @return String 仕入番号
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * 仕入番号設定
	 * @param slipNo 仕入番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * ロット番号取得
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * ロット番号設定
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 仕入数量取得
	 * @return BigDecimal 仕入数量
	 */
	public BigDecimal getStockingQuantity() {
		return this.stockingQuantity;
	}

	/**
	 * 仕入数量設定
	 * @param stockingQuantity 仕入数量
	 */
	public void setStockingQuantity(final BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
	}

	/**
	 * 単価取得
	 * @return BigDecimal 単価
	 */
	public BigDecimal getHousingUnitprice() {
		return this.housingUnitprice;
	}

	/**
	 * 単価設定
	 * @param housingUnitprice 単価
	 */
	public void setHousingUnitprice(final BigDecimal housingUnitprice) {
		this.housingUnitprice = housingUnitprice;
	}

	/**
	 * 金額取得
	 * @return BigDecimal 金額
	 */
	public BigDecimal getStockingAmount() {
		return this.stockingAmount;
	}

	/**
	 * 金額設定
	 * @param stockingAmount 金額
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * 受入日取得
	 * @return Timestamp 受入日
	 */
	public Timestamp getAcceptDate() {
		return this.acceptDate;
	}

	/**
	 * 受入日設定
	 * @param acceptDate 受入日
	 */
	public void setAcceptDate(final Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	/**
	 * 品目コード取得
	 * @return itemCd 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 購買番号取得
	 * @return purchaseNo 購買番号
	 */
	public String getPurchaseNo() {
		return purchaseNo;
	}

	/**
	 * 購買番号設定
	 * @param purchaseNo 購買番号
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 部門コード取得
	 * @return sectionCd 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 仕入先コード取得
	 * @return venderCd 仕入先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 仕入先コード設定
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 発注担当者コード取得
	 * @return tantoCd 発注担当者コード
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 発注担当者コード設定
	 * @param tantoCd 発注担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	// /**
	// * ロケーションコード取得
	// * @return housingLocationCd ロケーションコード
	// */
	// public String getHousingLocationCd() {
	// return housingLocationCd;
	// }
	//
	// /**
	// * ロケーションコード設定
	// * @param housingLocationCd ロケーションコード
	// */
	// public void setHousingLocationCd(final String housingLocationCd) {
	// this.housingLocationCd = housingLocationCd;
	// }

	/**
	 * 摘要(備考)取得
	 * @return remark2 摘要
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 摘要(備考)設定
	 * @param remark2 remark2 摘要
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 担当部署コード取得
	 * @return chargeOrganizationCd 担当部署コード
	 */
	public String getChargeOrganizationCd() {
		return chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定
	 * @param chargeOrganizationCd chargeOrganizationCd 担当部署コード
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 仕入ステータス取得
	 * @return status2 仕入ステータス
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * 仕入ステータス設定
	 * @param status2 仕入ステータス
	 */
	public void setStatus2(final String status2) {
		this.status2 = status2;
	}

	/**
	 * 買掛更新フラグ取得
	 * @return payableUpdateDivision 買掛更新フラグ
	 */
	public String getPayableUpdateDivision() {
		return payableUpdateDivision;
	}

	/**
	 * 買掛更新フラグ設定
	 * @param payableUpdateDivision 買掛更新フラグ
	 */
	public void setPayableUpdateDivision(final String payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * 支払更新フラグ取得
	 * @return paymentUpdateDivision 支払更新フラグ
	 */
	public String getPaymentUpdateDivision() {
		return paymentUpdateDivision;
	}

	/**
	 * 支払更新フラグ設定
	 * @param paymentUpdateDivision 支払更新フラグ
	 */
	public void setPaymentUpdateDivision(final String paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

	/**
	 * slipIssueDivision取得
	 * @return slipIssueDivision slipIssueDivision
	 */
	public BigDecimal getSlipIssueDivision() {
		return slipIssueDivision;
	}

	/**
	 * slipIssueDivision設定
	 * @param slipIssueDivision slipIssueDivision
	 */
	public void setSlipIssueDivision(final BigDecimal slipIssueDivision) {
		this.slipIssueDivision = slipIssueDivision;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * taxCdを取得します。
	 * @return taxCd
	 */
	public String getTaxCd() {
		return taxCd;
	}

	/**
	 * taxCdを設定します。
	 * @param taxCd taxCd
	 */
	public void setTaxCd(String taxCd) {
		this.taxCd = taxCd;
	}
}
