/*
 * Created on 2009/02/17
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
 * 受入仕入画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class AcceptListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptListBase() {
	}

	//
	// 定数
	//

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

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

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

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション buttonStatus */
	public static final String buttonStatus_COLUMN = "BUTTON_STATUS";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション replyContentsDivision */
	public static final String replyContentsDivision_COLUMN = "REPLY_CONTENTS_DIVISION";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション acceptQuantity */
	public static final String acceptQuantity_COLUMN = "ACCEPT_QUANTITY";

	/** COLUMNアノテーション acceptConvertQuantity. */
	public static final String acceptConvertQuantity_COLUMN = "ACCEPT_CONVERT_QUANTITY";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション orderSheetRemark2 */
	public static final String orderSheetRemark2_COLUMN = "ORDER_SHEET_REMARK2";

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

	/** オーダー区分 */
	private BigDecimal orderDivision;

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

	/** 購買ステータス */
	private BigDecimal status;

	/** 購買ステータス */
	private BigDecimal buttonStatus;

	/** 発注書NO */
	private String orderSheetNo;

	/** 分納区分|0:無し 1:有り */
	private BigDecimal replyContentsDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 仕入番号 */
	private String slipNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 受入数量 */
	private BigDecimal acceptQuantity;

	/** 受入重量 */
	private BigDecimal acceptConvertQuantity;

	/** 受入日付 */
	private Timestamp acceptDate;

	/** 仕入ステータス */
	private BigDecimal status2;

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
	 * オーダー区分取得
	 * @return BigDecimal オーダー区分
	 */
	public BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * オーダー区分設定
	 * @param orderDivision オーダー区分
	 */
	public void setOrderDivision(final BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
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
	 * 購買ステータス取得
	 * @return BigDecimal 購買ステータス
	 */
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定
	 * @param status 購買ステータス
	 */
	public void setStatus(final BigDecimal status) {
		this.status = status;
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
	 * 分類コード取得.
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定.
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * ボタンステータス取得
	 * @return BigDecimal ボタンステータス
	 */
	public BigDecimal getButtonStatus() {
		return buttonStatus;
	}

	/**
	 * ボタンステータス設定
	 * @param buttonStatus 仕入ステータス
	 */
	public void setButtonStatus(final BigDecimal buttonStatus) {
		this.buttonStatus = buttonStatus;
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
