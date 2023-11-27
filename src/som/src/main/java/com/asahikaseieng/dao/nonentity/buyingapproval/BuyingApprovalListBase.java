/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.buyingapproval;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 仕入承認画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class BuyingApprovalListBase implements Serializable {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalListBase() {
	}


	//
	// 定数
	//


	/** TABLEアノテーション.*/
	public static final String TABLE = "PURCHASE_SUBCONTRACT";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション supplierOrdAmount */
	public static final String supplierOrdAmount_COLUMN = "SUPPLIER_ORD_AMOUNT";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション stockingDate */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 受注番号 */
	private String orderNo;

	/** 仕入先コード */
	private String venderCd;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 発注金額 */
	private BigDecimal supplierOrdAmount;

	/** 発注書NO */
	private String orderSheetNo;

	/** 仕入日付 */
	private Timestamp stockingDate;

	/** 仕入番号 */
	private String slipNo;

	/** 仕入数量 */
	private BigDecimal stockingQuantity;

	/** 仕入単価 */
	private BigDecimal housingUnitprice;

	/** 仕入金額 */
	private BigDecimal stockingAmount;

	/** 受入日付 */
	private Timestamp acceptDate;

	/** 仕入ステータス */
	private BigDecimal status2;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	/** 承認者 */
	private String approvedby;

	/** 承認日 */
	private Timestamp approvaldate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

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
	 * 受注番号取得
	 * @return String 受注番号
	*/
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	*/
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
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
	 * 発注金額取得
	 * @return BigDecimal 発注金額
	*/
	public BigDecimal getSupplierOrdAmount() {
		return this.supplierOrdAmount;
	}

	/**
	 * 発注金額設定
	 * @param supplierOrdAmount 発注金額
	*/
	public void setSupplierOrdAmount(final BigDecimal supplierOrdAmount) {
		this.supplierOrdAmount = supplierOrdAmount;
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
	 * 承認ステータス取得
	 * @return BigDecimal 承認ステータス
	*/
	public BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * 承認ステータス設定
	 * @param approvalStatus 承認ステータス
	*/
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 承認者取得
	 * @return String 承認者
	*/
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * 承認者設定
	 * @param approvedby 承認者
	*/
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * 承認日取得
	 * @return Timestamp 承認日
	*/
	public Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * 承認日設定
	 * @param approvaldate 承認日
	*/
	public void setApprovaldate(final Timestamp approvaldate) {
		this.approvaldate = approvaldate;
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
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
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
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
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

}
