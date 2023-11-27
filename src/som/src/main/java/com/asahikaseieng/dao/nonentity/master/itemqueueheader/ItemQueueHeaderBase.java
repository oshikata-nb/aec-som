/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueueheader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemQueueHeaderクラス.
 * @author t0011036
 */
public class ItemQueueHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション headItemCd */
	public static final String headItemCd_COLUMN = "HEAD_ITEM_CD";

	/** COLUMNアノテーション headVersion */
	public static final String headVersion_COLUMN = "HEAD_VERSION";

	/** COLUMNアノテーション headItemName */
	public static final String headItemName_COLUMN = "HEAD_ITEM_NAME";

	/** COLUMNアノテーション shortItemName */
	public static final String shortItemName_COLUMN = "SHORT_ITEM_NAME";

	/** COLUMNアノテーション headActiveDate */
	public static final String headActiveDate_COLUMN = "HEAD_ACTIVE_DATE";

	/** COLUMNアノテーション headActivate */
	public static final String headActivate_COLUMN = "HEAD_ACTIVATE";

	/** COLUMNアノテーション productDivision */
	public static final String productDivision_COLUMN = "PRODUCT_DIVISION";

	/** COLUMNアノテーション articleDivision */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** COLUMNアノテーション purchaseDivision */
	public static final String purchaseDivision_COLUMN = "PURCHASE_DIVISION";

	/** COLUMNアノテーション headerStatus */
	public static final String headerStatus_COLUMN = "HEADER_STATUS";

	/** COLUMNアノテーション headDetailStatusName */
	public static final String headDetailStatusName_COLUMN = "HEAD_DETAIL_STATUS_NAME";

	/** COLUMNアノテーション headAttributeStatusName */
	public static final String headAttributeStatusName_COLUMN = "HEAD_ATTRIBUTE_STATUS_NAME";

	//
	// インスタンスフィールド
	//

	private String headItemCd;

	private java.math.BigDecimal headVersion;

	private String headItemName;

	private String shortItemName;

	private java.sql.Timestamp headActiveDate;

	private String headActivate;

	private java.math.BigDecimal productDivision;

	private java.math.BigDecimal articleDivision;

	private java.math.BigDecimal purchaseDivision;

	private java.math.BigDecimal headerStatus;

	private String headDetailStatusName;

	private String headAttributeStatusName;

	//
	// インスタンスメソッド
	//

	/**
	 * headItemCd取得.
	 * @return headItemCd
	 */
	public String getHeadItemCd() {
		return this.headItemCd;
	}

	/**
	 * headItemCd設定.
	 * @param headItemCd headItemCd
	 */
	public void setHeadItemCd(final String headItemCd) {
		this.headItemCd = headItemCd;
	}

	/**
	 * headVersion取得.
	 * @return headVersion
	 */
	public java.math.BigDecimal getHeadVersion() {
		return this.headVersion;
	}

	/**
	 * headVersion設定.
	 * @param headVersion headVersion
	 */
	public void setHeadVersion(final java.math.BigDecimal headVersion) {
		this.headVersion = headVersion;
	}

	/**
	 * headItemName取得.
	 * @return headItemName
	 */
	public String getHeadItemName() {
		return this.headItemName;
	}

	/**
	 * headItemName設定.
	 * @param headItemName headItemName
	 */
	public void setHeadItemName(final String headItemName) {
		this.headItemName = headItemName;
	}

	/**
	 * shortItemName取得.
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return this.shortItemName;
	}

	/**
	 * shortItemName設定.
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
	}

	/**
	 * headActiveDate取得.
	 * @return headActiveDate
	 */
	public java.sql.Timestamp getHeadActiveDate() {
		return this.headActiveDate;
	}

	/**
	 * headActiveDate設定.
	 * @param headActiveDate headActiveDate
	 */
	public void setHeadActiveDate(final java.sql.Timestamp headActiveDate) {
		this.headActiveDate = headActiveDate;
	}

	/**
	 * headActivate取得.
	 * @return headActivate
	 */
	public String getHeadActivate() {
		return this.headActivate;
	}

	/**
	 * headActivate設定.
	 * @param headActivate headActivate
	 */
	public void setHeadActivate(final String headActivate) {
		this.headActivate = headActivate;
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
	 * headerStatus取得.
	 * @return headerStatus
	 */
	public java.math.BigDecimal getHeaderStatus() {
		return this.headerStatus;
	}

	/**
	 * headerStatus設定.
	 * @param headerStatus headerStatus
	 */
	public void setHeaderStatus(final java.math.BigDecimal headerStatus) {
		this.headerStatus = headerStatus;
	}

	/**
	 * headDetailStatusName取得.
	 * @return headDetailStatusName
	 */
	public String getHeadDetailStatusName() {
		return this.headDetailStatusName;
	}

	/**
	 * headDetailStatusName設定.
	 * @param headDetailStatusName headDetailStatusName
	 */
	public void setHeadDetailStatusName(final String headDetailStatusName) {
		this.headDetailStatusName = headDetailStatusName;
	}

	/**
	 * headAttributeStatusName取得.
	 * @return headAttributeStatusName
	 */
	public String getHeadAttributeStatusName() {
		return this.headAttributeStatusName;
	}

	/**
	 * headAttributeStatusName設定.
	 * @param headAttributeStatusName headAttributeStatusName
	 */
	public void setHeadAttributeStatusName(final String headAttributeStatusName) {
		this.headAttributeStatusName = headAttributeStatusName;
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

