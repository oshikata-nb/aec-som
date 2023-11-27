/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemRelatedMgrecipeListクラス.
 * @author t0011036
 */
public class ItemRelatedMgrecipeListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedMgrecipeListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション mgrecipeCd */
	public static final String mgrecipeCd_COLUMN = "MGRECIPE_CD";

	/** COLUMNアノテーション mgrecipeVersion */
	public static final String mgrecipeVersion_COLUMN = "MGRECIPE_VERSION";

	/** COLUMNアノテーション mgrecipeItemCd */
	public static final String mgrecipeItemCd_COLUMN = "MGRECIPE_ITEM_CD";

	/** COLUMNアノテーション mgrecipeItemName */
	public static final String mgrecipeItemName_COLUMN = "MGRECIPE_ITEM_NAME";

	/** COLUMNアノテーション mgrecipeUpdateDate */
	public static final String mgrecipeUpdateDate_COLUMN = "MGRECIPE_UPDATE_DATE";

	/** COLUMNアノテーション mgrecipeStatusName */
	public static final String mgrecipeStatusName_COLUMN = "MGRECIPE_STATUS_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String mgrecipeCd;

	private java.math.BigDecimal mgrecipeVersion;

	private String mgrecipeItemCd;

	private String mgrecipeItemName;

	private java.sql.Timestamp mgrecipeUpdateDate;

	private String mgrecipeStatusName;

	//
	// インスタンスメソッド
	//

	/**
	 * recipeId取得.
	 * @return recipeId
	 */
	public java.math.BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * recipeId設定.
	 * @param recipeId recipeId
	 */
	public void setRecipeId(final java.math.BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * mgrecipeCd取得.
	 * @return mgrecipeCd
	 */
	public String getMgrecipeCd() {
		return this.mgrecipeCd;
	}

	/**
	 * mgrecipeCd設定.
	 * @param mgrecipeCd mgrecipeCd
	 */
	public void setMgrecipeCd(final String mgrecipeCd) {
		this.mgrecipeCd = mgrecipeCd;
	}

	/**
	 * mgrecipeVersion取得.
	 * @return mgrecipeVersion
	 */
	public java.math.BigDecimal getMgrecipeVersion() {
		return this.mgrecipeVersion;
	}

	/**
	 * mgrecipeVersion設定.
	 * @param mgrecipeVersion mgrecipeVersion
	 */
	public void setMgrecipeVersion(final java.math.BigDecimal mgrecipeVersion) {
		this.mgrecipeVersion = mgrecipeVersion;
	}

	/**
	 * mgrecipeItemCd取得.
	 * @return mgrecipeItemCd
	 */
	public String getMgrecipeItemCd() {
		return this.mgrecipeItemCd;
	}

	/**
	 * mgrecipeItemCd設定.
	 * @param mgrecipeItemCd mgrecipeItemCd
	 */
	public void setMgrecipeItemCd(final String mgrecipeItemCd) {
		this.mgrecipeItemCd = mgrecipeItemCd;
	}

	/**
	 * mgrecipeItemName取得.
	 * @return mgrecipeItemName
	 */
	public String getMgrecipeItemName() {
		return this.mgrecipeItemName;
	}

	/**
	 * mgrecipeItemName設定.
	 * @param mgrecipeItemName mgrecipeItemName
	 */
	public void setMgrecipeItemName(final String mgrecipeItemName) {
		this.mgrecipeItemName = mgrecipeItemName;
	}

	/**
	 * mgrecipeUpdateDate取得.
	 * @return mgrecipeUpdateDate
	 */
	public java.sql.Timestamp getMgrecipeUpdateDate() {
		return this.mgrecipeUpdateDate;
	}

	/**
	 * mgrecipeUpdateDate設定.
	 * @param mgrecipeUpdateDate mgrecipeUpdateDate
	 */
	public void setMgrecipeUpdateDate(final java.sql.Timestamp mgrecipeUpdateDate) {
		this.mgrecipeUpdateDate = mgrecipeUpdateDate;
	}

	/**
	 * mgrecipeStatusName取得.
	 * @return mgrecipeStatusName
	 */
	public String getMgrecipeStatusName() {
		return this.mgrecipeStatusName;
	}

	/**
	 * mgrecipeStatusName設定.
	 * @param mgrecipeStatusName mgrecipeStatusName
	 */
	public void setMgrecipeStatusName(final String mgrecipeStatusName) {
		this.mgrecipeStatusName = mgrecipeStatusName;
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

