/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 生産計画入力画面_ヘッダ部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ProductionDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductionDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	//
	// インスタンスフィールド
	//

	/** 荷主 */
	private BigDecimal shipperDivision;

	/** 社内製造品/外注品区分　*/
	private BigDecimal typeDivision;

	/** 工場名　*/
	private String productionLineName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 運用管理区分　*/
	private String unitOfOperationManagement;

	/** 単位 */
	private String unit;

	/** 生産計画年月 */
	private Timestamp orderLet;

	/** 見込み数量合計 */
	private BigDecimal sumExpectQty;

	/** 受注数量合計 */
	private BigDecimal sumAcceptQty;

	/** 見込み数量合計 +　受注数量合計 */
	private BigDecimal sumAllOrderQty;

	//
	// インスタンスメソッド
	//
	/**
	 * 品目コード取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 生産計画年月を取得します。
	 * @return orderLet
	 */
	public Timestamp getOrderLet() {
		return orderLet;
	}

	/**
	 * 生産計画年月を設定します。
	 * @param orderLet 生産計画年月
	 */
	public void setOrderLet(final Timestamp orderLet) {
		this.orderLet = orderLet;
	}

	/**
	 * 他社コード１を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 運用管理区分を取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理区分を設定します。
	 * @param unitOfOperationManagement 運用管理区分
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 工場名を取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 工場名を設定します。
	 * @param productionLineName 工場名
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 荷主を取得します。
	 * @return shipperDivision
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主を設定します。
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 社内製造品/外注品区分を取得します。
	 * @return typeDivision
	 */
	public BigDecimal getTypeDivision() {
		return typeDivision;
	}

	/**
	 * 社内製造品/外注品区分を設定します。
	 * @param typeDivision 社内製造品/外注品区分
	 */
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * 受注数量合計を取得します。
	 * @return sumAcceptQty
	 */
	public BigDecimal getSumAcceptQty() {
		return sumAcceptQty;
	}

	/**
	 * 受注数量合計を設定します。
	 * @param sumAcceptQty 受注数量合計
	 */
	public void setSumAcceptQty(final BigDecimal sumAcceptQty) {
		this.sumAcceptQty = sumAcceptQty;
	}

	/**
	 * 見込み数量合計 +　受注数量合計を取得します。
	 * @return sumAllOrderQty
	 */
	public BigDecimal getSumAllOrderQty() {
		return sumAllOrderQty;
	}

	/**
	 * 見込み数量合計 +　受注数量合計を設定します。
	 * @param sumAllOrderQty 見込み数量合計 +　受注数量合計
	 */
	public void setSumAllOrderQty(final BigDecimal sumAllOrderQty) {
		this.sumAllOrderQty = sumAllOrderQty;
	}

	/**
	 * 見込み数量合計を取得します。
	 * @return sumExpectQty
	 */
	public BigDecimal getSumExpectQty() {
		return sumExpectQty;
	}

	/**
	 * 見込み数量合計を設定します。
	 * @param sumExpectQty 見込み数量合計
	 */
	public void setSumExpectQty(final BigDecimal sumExpectQty) {
		this.sumExpectQty = sumExpectQty;
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
	 * 単位を取得します。
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位を設定します。
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}
}
