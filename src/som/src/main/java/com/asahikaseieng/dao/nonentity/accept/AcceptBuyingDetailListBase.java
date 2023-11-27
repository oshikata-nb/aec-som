/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 受入・仕入 仕入入力画面_表示用データ格納クラス.
 * 
 * @author tosco
 */
public class AcceptBuyingDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptBuyingDetailListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PURCHASE_SUBCONTRACT";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderDivideNo */
	public static final String orderDivideNo_COLUMN = "ORDER_DIVIDE_NO";

	/** COLUMNアノテーション materialDivision. */
	public static final String materialDivision_COLUMN = "MATERIAL_DIVISION";

	/** COLUMNアノテーション siOrderNo */
	public static final String siOrderNo_COLUMN = "SI_ORDER_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション chargeOrganizationCd */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション suggestedDeliverlimitDate */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション replyContentsDivision */
	public static final String replyContentsDivision_COLUMN = "REPLY_CONTENTS_DIVISION";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション nextDeliverlimitDate */
	public static final String nextDeliverlimitDate_COLUMN = "NEXT_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション stockingDate. */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション supplierLotno */
	public static final String supplierLotno_COLUMN = "SUPPLIER_LOTNO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション stockLocationCd */
	public static final String stockLocationCd_COLUMN = "STOCK_LOCATION_CD";

	/** COLUMNアノテーション housingLocationCd */
	public static final String housingLocationCd_COLUMN = "HOUSING_LOCATION_CD";

	/** COLUMNアノテーション acceptQuantity */
	public static final String acceptQuantity_COLUMN = "ACCEPT_QUANTITY";

	/** COLUMNアノテーション acceptConvertQuantity. */
	public static final String acceptConvertQuantity_COLUMN = "ACCEPT_CONVERT_QUANTITY";

	/** COLUMNアノテーション increaseQuantity */
	public static final String increaseQuantity_COLUMN = "INCREASE_QUANTITY";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション fareAmount */
	public static final String fareAmount_COLUMN = "FARE_AMOUNT";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション orderSheetRemark2 */
	public static final String orderSheetRemark2_COLUMN = "ORDER_SHEET_REMARK2";

	/** COLUMNアノテーション remark2 */
	public static final String remark2_COLUMN = "REMARK2";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション accountDebitSectionCd */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountDreditSectionCd */
	public static final String accountDreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String paymentInvoiceCd_COLUMN = "PAYMENT_INVOICE_CD";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注番号分納枝番 */
	private String orderDivideNo;

	/** 外注原材料区分 */
	private BigDecimal materialDivision;

	/** 仕入先受注番 */
	private String siOrderNo;

	/** 発注日 */
	private Timestamp orderDate;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 部署コード */
	private String organizationCd;

	/** 発注担当者コード */
	private String tantoCd;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 納品希望日 */
	private Timestamp suggestedDeliverlimitDate;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 発注書NO */
	private String orderSheetNo;

	/** 分納区分|0:無し 1:有り */
	private BigDecimal replyContentsDivision;

	/** 完納区分 */
	private BigDecimal deliveryComp;

	/** 次回納品希望日 */
	private Timestamp nextDeliverlimitDate;

	/** 分類コード */
	private String categoryDivision;

	/** 仕入日付 */
	private Timestamp stockingDate;

	/** 仕入番号 */
	private String slipNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** メーカーロット番号 */
	private String supplierLotno;

	/** 入荷ロット番号 */
	private String lotNo;

	/** 入庫ロケーションコード(I/Fから登録) */
	private String stockLocationCd;

	/** 入庫ロケーションコード */
	private String housingLocationCd;

	/** 受入数量 */
	private BigDecimal acceptQuantity;

	/** 受入重量 */
	private BigDecimal acceptConvertQuantity;

	/** 増付数量 */
	private BigDecimal increaseQuantity;

	/** 仕入数量 */
	private BigDecimal stockingQuantity;

	/** 仕入単価 */
	private BigDecimal housingUnitprice;

	/** 運賃 */
	private BigDecimal fareAmount;

	/** 仕入金額 */
	private BigDecimal stockingAmount;

	/** 受入日付 */
	private Timestamp acceptDate;

	/** 発注書備考（入荷以降） */
	private String orderSheetRemark2;

	/** 備考（入荷以降） */
	private String remark2;

	/** 仕入ステータス */
	private BigDecimal status2;

	/** 会計部門借方コード */
	private String accountDebitSectionCd;

	/** 会計部門貸方コード */
	private String accountDreditSectionCd;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者 */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者 */
	private String updatorCd;

	/** 支払先コード */
	private String paymentInvoiceCd;

	/** 消費税額 */
	private BigDecimal taxAmount;

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注番号分納枝番取得
	 * @return String 発注番号分納枝番
	 */
	public String getOrderDivideNo() {
		return this.orderDivideNo;
	}

	/**
	 * 発注番号分納枝番設定
	 * @param orderDivideNo 発注番号分納枝番
	 */
	public void setOrderDivideNo(final String orderDivideNo) {
		this.orderDivideNo = orderDivideNo;
	}

	/**
	 * 外注原材料区分取得.
	 * @return BigDecimal 外注原材料区分
	 */
	public BigDecimal getMaterialDivision() {
		return this.materialDivision;
	}

	/**
	 * 外注原材料区分設定.
	 * @param materialDivision 外注原材料区分
	 */
	public void setMaterialDivision(final BigDecimal materialDivision) {
		this.materialDivision = materialDivision;
	}

	/**
	 * 仕入先受注番取得
	 * @return String 仕入先受注番
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定
	 * @param siOrderNo 仕入先受注番
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
	}

	/**
	 * 発注日取得
	 * @return Timestamp 発注日
	 */
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * 発注日設定
	 * @param orderDate 発注日
	 */
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 担当部署コード取得
	 * @return String 担当部署コード
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定
	 * @param chargeOrganizationCd 担当部署コード
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 部署コード取得
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * 部署コード設定
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 発注担当者コード取得
	 * @return String 発注担当者コード
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 発注担当者コード設定
	 * @param tantoCd 発注担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 仕入先コード取得
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 発注数量取得
	 * @return BigDecimal 発注数量
	 */
	public BigDecimal getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定
	 * @param orderQuantity 発注数量
	 */
	public void setOrderQuantity(final BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 重量取得
	 * @return BigDecimal 重量
	 */
	public BigDecimal getOrderConvertQuantity() {
		return this.orderConvertQuantity;
	}

	/**
	 * 重量設定
	 * @param orderConvertQuantity 重量
	 */
	public void setOrderConvertQuantity(final BigDecimal orderConvertQuantity) {
		this.orderConvertQuantity = orderConvertQuantity;
	}

	/**
	 * 納品希望日取得
	 * @return Timestamp 納品希望日
	 */
	public Timestamp getSuggestedDeliverlimitDate() {
		return this.suggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定
	 * @param suggestedDeliverlimitDate 納品希望日
	 */
	public void setSuggestedDeliverlimitDate(
			final Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

	/**
	 * 納入ロケーションコード取得
	 * @return String 納入ロケーションコード
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * 納入ロケーションコード設定
	 * @param locationCd 納入ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 発注書NO取得
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 分納区分|0:無し 1:有り取得
	 * @return BigDecimal 分納区分|0:無し 1:有り
	 */
	public BigDecimal getReplyContentsDivision() {
		return this.replyContentsDivision;
	}

	/**
	 * 分納区分|0:無し 1:有り設定
	 * @param replyContentsDivision 分納区分|0:無し 1:有り
	 */
	public void setReplyContentsDivision(final BigDecimal replyContentsDivision) {
		this.replyContentsDivision = replyContentsDivision;
	}

	/**
	 * 完納区分取得
	 * @return BigDecimal 完納区分
	 */
	public BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * 完納区分設定
	 * @param deliveryComp 完納区分
	 */
	public void setDeliveryComp(final BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * 次回納品希望日取得
	 * @return Timestamp 次回納品希望日
	 */
	public Timestamp getNextDeliverlimitDate() {
		return this.nextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日設定
	 * @param nextDeliverlimitDate 次回納品希望日
	 */
	public void setNextDeliverlimitDate(final Timestamp nextDeliverlimitDate) {
		this.nextDeliverlimitDate = nextDeliverlimitDate;
	}

	/**
	 * 分類コード取得
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コード設定.
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 仕入日付取得
	 * @return Timestamp 仕入日付
	 */
	public Timestamp getStockingDate() {
		return this.stockingDate;
	}

	/**
	 * 仕入日付設定
	 * @param stockingDate 仕入日付
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
	 * 行番号取得
	 * @return BigDecimal 行番号
	 */
	public BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * 行番号設定
	 * @param rowNo 行番号
	 */
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * メーカーロット番号取得
	 * @return String メーカーロット番号
	 */
	public String getSupplierLotno() {
		return this.supplierLotno;
	}

	/**
	 * メーカーロット番号設定
	 * @param supplierLotno メーカーロット番号
	 */
	public void setSupplierLotno(final String supplierLotno) {
		this.supplierLotno = supplierLotno;
	}

	/**
	 * 入荷ロット番号取得
	 * @return String 入荷ロット番号
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * 入荷ロット番号設定
	 * @param lotNo 入荷ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 入庫ロケーションコード(I/Fから登録)取得
	 * @return String 入庫ロケーションコード(I/Fから登録)
	 */
	public String getStockLocationCd() {
		return this.stockLocationCd;
	}

	/**
	 * 入庫ロケーションコード(I/Fから登録)設定
	 * @param stockLocationCd 入庫ロケーションコード(I/Fから登録)
	 */
	public void setStockLocationCd(final String stockLocationCd) {
		this.stockLocationCd = stockLocationCd;
	}

	/**
	 * 入庫ロケーションコード取得
	 * @return String 入庫ロケーションコード
	 */
	public String getHousingLocationCd() {
		return this.housingLocationCd;
	}

	/**
	 * 入庫ロケーションコード設定
	 * @param housingLocationCd 入庫ロケーションコード
	 */
	public void setHousingLocationCd(final String housingLocationCd) {
		this.housingLocationCd = housingLocationCd;
	}

	/**
	 * 受入数量取得
	 * @return BigDecimal 受入数量
	 */
	public BigDecimal getAcceptQuantity() {
		return this.acceptQuantity;
	}

	/**
	 * 受入数量設定
	 * @param acceptQuantity 受入数量
	 */
	public void setAcceptQuantity(final BigDecimal acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	/**
	 * 受入重量取得
	 * @return BigDecimal 受入重量
	 */
	public BigDecimal getAcceptConvertQuantity() {
		return this.acceptConvertQuantity;
	}

	/**
	 * 受入重量設定
	 * @param acceptConvertQuantity 受入重量
	 */
	public void setAcceptConvertQuantity(final BigDecimal acceptConvertQuantity) {
		this.acceptConvertQuantity = acceptConvertQuantity;
	}

	/**
	 * 増付数量取得
	 * @return BigDecimal 増付数量
	 */
	public BigDecimal getIncreaseQuantity() {
		return this.increaseQuantity;
	}

	/**
	 * 増付数量設定
	 * @param increaseQuantity 増付数量
	 */
	public void setIncreaseQuantity(final BigDecimal increaseQuantity) {
		this.increaseQuantity = increaseQuantity;
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
	 * 仕入単価取得
	 * @return BigDecimal 仕入単価
	 */
	public BigDecimal getHousingUnitprice() {
		return this.housingUnitprice;
	}

	/**
	 * 仕入単価設定
	 * @param housingUnitprice 仕入単価
	 */
	public void setHousingUnitprice(final BigDecimal housingUnitprice) {
		this.housingUnitprice = housingUnitprice;
	}

	/**
	 * 運賃取得
	 * @return BigDecimal 運賃
	 */
	public BigDecimal getFareAmount() {
		return this.fareAmount;
	}

	/**
	 * 運賃設定
	 * @param fareAmount 運賃
	 */
	public void setFareAmount(final BigDecimal fareAmount) {
		this.fareAmount = fareAmount;
	}

	/**
	 * 仕入金額取得
	 * @return BigDecimal 仕入金額
	 */
	public BigDecimal getStockingAmount() {
		return this.stockingAmount;
	}

	/**
	 * 仕入金額設定
	 * @param stockingAmount 仕入金額
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * 受入日付取得
	 * @return Timestamp 受入日付
	 */
	public Timestamp getAcceptDate() {
		return this.acceptDate;
	}

	/**
	 * 受入日付設定
	 * @param acceptDate 受入日付
	 */
	public void setAcceptDate(final Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	/**
	 * 発注書備考（入荷以降）取得
	 * @return String 発注書備考（入荷以降）
	 */
	public String getOrderSheetRemark2() {
		return this.orderSheetRemark2;
	}

	/**
	 * 発注書備考（入荷以降）設定
	 * @param orderSheetRemark2 発注書備考（入荷以降）
	 */
	public void setOrderSheetRemark2(final String orderSheetRemark2) {
		this.orderSheetRemark2 = orderSheetRemark2;
	}

	/**
	 * 備考（入荷以降）取得
	 * @return String 備考（入荷以降）
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * 備考（入荷以降）設定
	 * @param remark2 備考（入荷以降）
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 仕入ステータス取得
	 * @return BigDecimal 仕入ステータス
	 */
	public BigDecimal getStatus2() {
		return this.status2;
	}

	/**
	 * 仕入ステータス設定
	 * @param status2 仕入ステータス
	 */
	public void setStatus2(final BigDecimal status2) {
		this.status2 = status2;
	}

	/**
	 * 会計部門借方コード取得
	 * @return String 会計部門借方コード
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * 会計部門借方コード設定
	 * @param accountDebitSectionCd 会計部門借方コード
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門貸方コード取得
	 * @return String 会計部門貸方コード
	 */
	public String getAccountCreditSectionCd() {
		return this.accountDreditSectionCd;
	}

	/**
	 * 会計部門貸方コード設定
	 * @param accountDreditSectionCd 会計部門貸方コード
	 */
	public void setAccountCreditSectionCd(final String accountDreditSectionCd) {
		this.accountDreditSectionCd = accountDreditSectionCd;
	}

	/**
	 * 借方科目コード取得
	 * @return String 借方科目コード
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * 借方科目コード設定
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 貸方科目コード取得
	 * @return String 貸方科目コード
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * 貸方科目コード設定
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得
	 * @return String 登録者
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者設定
	 * @param inputorCd 登録者
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得
	 * @return String 更新者
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者設定
	 * @param updatorCd 更新者
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
	 * paymentInvoiceCdを取得します。
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCdを設定します。
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * taxAmountを取得します。
	 * @return taxAmount
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * taxAmountを設定します。
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

}
