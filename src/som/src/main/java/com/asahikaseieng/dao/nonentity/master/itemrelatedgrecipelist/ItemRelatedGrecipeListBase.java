/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemRelatedGrecipeListクラス.
 * @author t0011036
 */
public class ItemRelatedGrecipeListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedGrecipeListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション grecipeCd */
	public static final String grecipeCd_COLUMN = "GRECIPE_CD";

	/** COLUMNアノテーション grecipeVersion */
	public static final String grecipeVersion_COLUMN = "GRECIPE_VERSION";

	/** COLUMNアノテーション grecipeItemCd */
	public static final String grecipeItemCd_COLUMN = "GRECIPE_ITEM_CD";

	/** COLUMNアノテーション grecipeItemName */
	public static final String grecipeItemName_COLUMN = "GRECIPE_ITEM_NAME";

	/** COLUMNアノテーション grecipeUpdateDate */
	public static final String grecipeUpdateDate_COLUMN = "GRECIPE_UPDATE_DATE";

	/** COLUMNアノテーション grecipeStatusName */
	public static final String grecipeStatusName_COLUMN = "GRECIPE_STATUS_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String grecipeCd;

	private java.math.BigDecimal grecipeVersion;

	private String grecipeItemCd;

	private String grecipeItemName;

	private java.sql.Timestamp grecipeUpdateDate;

	private String grecipeStatusName;

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
	 * grecipeCd取得.
	 * @return grecipeCd
	 */
	public String getGrecipeCd() {
		return this.grecipeCd;
	}

	/**
	 * grecipeCd設定.
	 * @param grecipeCd grecipeCd
	 */
	public void setGrecipeCd(final String grecipeCd) {
		this.grecipeCd = grecipeCd;
	}

	/**
	 * grecipeVersion取得.
	 * @return grecipeVersion
	 */
	public java.math.BigDecimal getGrecipeVersion() {
		return this.grecipeVersion;
	}

	/**
	 * grecipeVersion設定.
	 * @param grecipeVersion grecipeVersion
	 */
	public void setGrecipeVersion(final java.math.BigDecimal grecipeVersion) {
		this.grecipeVersion = grecipeVersion;
	}

	/**
	 * grecipeItemCd取得.
	 * @return grecipeItemCd
	 */
	public String getGrecipeItemCd() {
		return this.grecipeItemCd;
	}

	/**
	 * grecipeItemCd設定.
	 * @param grecipeItemCd grecipeItemCd
	 */
	public void setGrecipeItemCd(final String grecipeItemCd) {
		this.grecipeItemCd = grecipeItemCd;
	}

	/**
	 * grecipeItemName取得.
	 * @return grecipeItemName
	 */
	public String getGrecipeItemName() {
		return this.grecipeItemName;
	}

	/**
	 * grecipeItemName設定.
	 * @param grecipeItemName grecipeItemName
	 */
	public void setGrecipeItemName(final String grecipeItemName) {
		this.grecipeItemName = grecipeItemName;
	}

	/**
	 * grecipeUpdateDate取得.
	 * @return grecipeUpdateDate
	 */
	public java.sql.Timestamp getGrecipeUpdateDate() {
		return this.grecipeUpdateDate;
	}

	/**
	 * grecipeUpdateDate設定.
	 * @param grecipeUpdateDate grecipeUpdateDate
	 */
	public void setGrecipeUpdateDate(final java.sql.Timestamp grecipeUpdateDate) {
		this.grecipeUpdateDate = grecipeUpdateDate;
	}

	/**
	 * grecipeStatusName取得.
	 * @return grecipeStatusName
	 */
	public String getGrecipeStatusName() {
		return this.grecipeStatusName;
	}

	/**
	 * grecipeStatusName設定.
	 * @param grecipeStatusName grecipeStatusName
	 */
	public void setGrecipeStatusName(final String grecipeStatusName) {
		this.grecipeStatusName = grecipeStatusName;
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

