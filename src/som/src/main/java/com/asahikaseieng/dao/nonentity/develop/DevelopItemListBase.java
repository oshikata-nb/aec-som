/*
 * Created on 2007/12/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DevelopItemListクラス.
 * @author zen
 */
public class DevelopItemListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopItemListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemSubName */
	public static final String itemSubName_COLUMN = "ITEM_SUB_NAME";

	/** COLUMNアノテーション productDivision */
	public static final String productDivision_COLUMN = "PRODUCT_DIVISION";

	/** COLUMNアノテーション articleDivision */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** COLUMNアノテーション purchaseDivision */
	public static final String purchaseDivision_COLUMN = "PURCHASE_DIVISION";

	/** COLUMNアノテーション itemUnit */
	public static final String itemUnit_COLUMN = "ITEM_UNIT";

	/** COLUMNアノテーション unitWeight */
	public static final String unitWeight_COLUMN = "UNIT_WEIGHT";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション spotDivision */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション bulkDivision */
	public static final String bulkDivision_COLUMN = "BULK_DIVISION";

	/** COLUMNアノテーション defaultLocation */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション costDivision */
	public static final String costDivision_COLUMN = "COST_DIVISION";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** COLUMNアノテーション newFlg */
	public static final String newFlg_COLUMN = "NEW_FLG";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション developmentRequestNo */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.sql.Timestamp activeDate;

	private java.math.BigDecimal status;

	private String itemName;

	private String itemSubName;

	private java.math.BigDecimal productDivision;

	private java.math.BigDecimal articleDivision;

	private java.math.BigDecimal purchaseDivision;

	private String itemUnit;

	private java.math.BigDecimal unitWeight;

	private String itemCategory;

	private java.math.BigDecimal spotDivision;

	private java.math.BigDecimal stockDivision;

	private java.math.BigDecimal bulkDivision;

	private String defaultLocation;

	private java.math.BigDecimal costDivision;

	private java.math.BigDecimal lotDivision;

	private java.math.BigDecimal newFlg;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String developmentRequestNo;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.品目コード
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.品目コード
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * activeDate取得.受注日
	 * @return activeDate
	 */
	public java.sql.Timestamp getActiveDate() {
		return this.activeDate;
	}

	/**
	 * activeDate設定.受注日
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final java.sql.Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * status取得.ステータス
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.ステータス
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * itemName取得.品目名称
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.品目名称
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * itemSubName取得.
	 * @return itemSubName
	 */
	public String getItemSubName() {
		return this.itemSubName;
	}

	/**
	 * itemSubName設定.
	 * @param itemSubName itemSubName
	 */
	public void setItemSubName(final String itemSubName) {
		this.itemSubName = itemSubName;
	}

	/**
	 * productDivision取得.
	 * @return productDivision
	 */
	public java.math.BigDecimal getProductDivision() {
		return this.productDivision;
	}

	/**
	 * productDivision設定.
	 * @param productDivision productDivision
	 */
	public void setProductDivision(final java.math.BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * articleDivision取得.
	 * @return articleDivision
	 */
	public java.math.BigDecimal getArticleDivision() {
		return this.articleDivision;
	}

	/**
	 * articleDivision設定.
	 * @param articleDivision articleDivision
	 */
	public void setArticleDivision(final java.math.BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * purchaseDivision取得.
	 * @return purchaseDivision
	 */
	public java.math.BigDecimal getPurchaseDivision() {
		return this.purchaseDivision;
	}

	/**
	 * purchaseDivision設定.
	 * @param purchaseDivision purchaseDivision
	 */
	public void setPurchaseDivision(final java.math.BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
	}

	/**
	 * itemUnit取得.単位
	 * @return itemUnit
	 */
	public String getItemUnit() {
		return this.itemUnit;
	}

	/**
	 * itemUnit設定.単位
	 * @param itemUnit itemUnit
	 */
	public void setItemUnit(final String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * unitWeight取得.単位重量
	 * @return unitWeight
	 */
	public java.math.BigDecimal getUnitWeight() {
		return this.unitWeight;
	}

	/**
	 * unitWeight設定.単位重量
	 * @param unitWeight unitWeight
	 */
	public void setUnitWeight(final java.math.BigDecimal unitWeight) {
		this.unitWeight = unitWeight;
	}

	/**
	 * itemCategory取得.大分類コード
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * itemCategory設定.大分類コード
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * spotDivision取得.
	 * @return spotDivision
	 */
	public java.math.BigDecimal getSpotDivision() {
		return this.spotDivision;
	}

	/**
	 * spotDivision設定.
	 * @param spotDivision spotDivision
	 */
	public void setSpotDivision(final java.math.BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * stockDivision取得.
	 * @return stockDivision
	 */
	public java.math.BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * stockDivision設定.
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final java.math.BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * bulkDivision取得.
	 * @return bulkDivision
	 */
	public java.math.BigDecimal getBulkDivision() {
		return this.bulkDivision;
	}

	/**
	 * bulkDivision設定.
	 * @param bulkDivision bulkDivision
	 */
	public void setBulkDivision(final java.math.BigDecimal bulkDivision) {
		this.bulkDivision = bulkDivision;
	}

	/**
	 * defaultLocation取得.納入ロケーションコード
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	/**
	 * defaultLocation設定.納入ロケーションコード
	 * @param defaultLocation defaultLocation
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * costDivision取得.原価分類区分
	 * @return costDivision
	 */
	public java.math.BigDecimal getCostDivision() {
		return this.costDivision;
	}

	/**
	 * costDivision設定.原価分類区分
	 * @param costDivision costDivision
	 */
	public void setCostDivision(final java.math.BigDecimal costDivision) {
		this.costDivision = costDivision;
	}

	/**
	 * lotDivision取得.
	 * @return lotDivision
	 */
	public java.math.BigDecimal getLotDivision() {
		return this.lotDivision;
	}

	/**
	 * lotDivision設定.
	 * @param lotDivision lotDivision
	 */
	public void setLotDivision(final java.math.BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}

	/**
	 * newFlg取得.
	 * @return newFlg
	 */
	public java.math.BigDecimal getNewFlg() {
		return this.newFlg;
	}

	/**
	 * newFlg設定.
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final java.math.BigDecimal newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * inputDate取得.登録日時
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.登録日時
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.登録者コード
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.登録者コード
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.更新日時
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.更新日時
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.更新者コード
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.更新者コード
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * developmentRequestNo取得.開発依頼番号
	 * @return developmentRequestNo
	 */
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * developmentRequestNo設定.開発依頼番号
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
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

