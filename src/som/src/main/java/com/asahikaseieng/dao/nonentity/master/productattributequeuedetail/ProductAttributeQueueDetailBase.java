/*
 * Created on 2010/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ProductAttributeQueueDetailクラス.
 * @author t0011036
 */
public class ProductAttributeQueueDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductAttributeQueueDetailBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/*  */
	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/*  */
	/** COLUMNアノテーション productStatus */
	public static final String productStatus_COLUMN = "PRODUCT_STATUS";

	/*  */
	/** COLUMNアノテーション orderPattern */
	public static final String orderPattern_COLUMN = "ORDER_PATTERN";

	/*  */
	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/*  */
	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/*  */
	/** COLUMNアノテーション inspectionDays */
	public static final String inspectionDays_COLUMN = "INSPECTION_DAYS";

	/*  */
	/** COLUMNアノテーション productOrderPoint */
	public static final String productOrderPoint_COLUMN = "PRODUCT_ORDER_POINT";

	/*  */
	/** COLUMNアノテーション productSectionCd */
	public static final String productSectionCd_COLUMN = "PRODUCT_SECTION_CD";

	/*  */
	/** COLUMNアノテーション productSectionName */
	public static final String productSectionName_COLUMN = "PRODUCT_SECTION_NAME";

	/*  */
	/** COLUMNアノテーション productUpdateDate */
	public static final String productUpdateDate_COLUMN = "PRODUCT_UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/**  */
	private String itemCd;

	/**  */
	private java.math.BigDecimal version;

	/**  */
	private java.math.BigDecimal productStatus;

	/**  */
	private java.math.BigDecimal orderPattern;

	/**  */
	private String productionLine;

	/**  */
	private String productionLineName;

	/**  */
	private java.math.BigDecimal inspectionDays;

	/**  */
	private java.math.BigDecimal productOrderPoint;

	/**  */
	private String productSectionCd;

	/**  */
	private String productSectionName;

	/**  */
	private java.sql.Timestamp productUpdateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * 
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * 
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * 
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * 
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * productStatus取得.
	 * 
	 * @return productStatus
	 */
	public java.math.BigDecimal getProductStatus() {
		return this.productStatus;
	}

	/**
	 * productStatus設定.
	 * 
	 * @param productStatus productStatus
	 */
	public void setProductStatus(final java.math.BigDecimal productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * orderPattern取得.
	 * 
	 * @return orderPattern
	 */
	public java.math.BigDecimal getOrderPattern() {
		return this.orderPattern;
	}

	/**
	 * orderPattern設定.
	 * 
	 * @param orderPattern orderPattern
	 */
	public void setOrderPattern(final java.math.BigDecimal orderPattern) {
		this.orderPattern = orderPattern;
	}

	/**
	 * productionLine取得.
	 * 
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * productionLine設定.
	 * 
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * productionLineName取得.
	 * 
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * 
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * inspectionDays取得.
	 * 
	 * @return inspectionDays
	 */
	public java.math.BigDecimal getInspectionDays() {
		return this.inspectionDays;
	}

	/**
	 * inspectionDays設定.
	 * 
	 * @param inspectionDays inspectionDays
	 */
	public void setInspectionDays(final java.math.BigDecimal inspectionDays) {
		this.inspectionDays = inspectionDays;
	}

	/**
	 * productOrderPoint取得.
	 * 
	 * @return productOrderPoint
	 */
	public java.math.BigDecimal getProductOrderPoint() {
		return this.productOrderPoint;
	}

	/**
	 * productOrderPoint設定.
	 * 
	 * @param productOrderPoint productOrderPoint
	 */
	public void setProductOrderPoint(final java.math.BigDecimal productOrderPoint) {
		this.productOrderPoint = productOrderPoint;
	}

	/**
	 * productSectionCd取得.
	 * 
	 * @return productSectionCd
	 */
	public String getProductSectionCd() {
		return this.productSectionCd;
	}

	/**
	 * productSectionCd設定.
	 * 
	 * @param productSectionCd productSectionCd
	 */
	public void setProductSectionCd(final String productSectionCd) {
		this.productSectionCd = productSectionCd;
	}

	/**
	 * productSectionName取得.
	 * 
	 * @return productSectionName
	 */
	public String getProductSectionName() {
		return this.productSectionName;
	}

	/**
	 * productSectionName設定.
	 * 
	 * @param productSectionName productSectionName
	 */
	public void setProductSectionName(final String productSectionName) {
		this.productSectionName = productSectionName;
	}

	/**
	 * productUpdateDate取得.
	 * 
	 * @return productUpdateDate
	 */
	public java.sql.Timestamp getProductUpdateDate() {
		return this.productUpdateDate;
	}

	/**
	 * productUpdateDate設定.
	 * 
	 * @param productUpdateDate productUpdateDate
	 */
	public void setProductUpdateDate(final java.sql.Timestamp productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
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

