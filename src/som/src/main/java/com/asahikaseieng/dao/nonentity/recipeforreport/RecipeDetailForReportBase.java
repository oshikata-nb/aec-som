/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeDetailForReportクラス.
 * @author kanri-user
 */
public class RecipeDetailForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeDetailForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション grecipeDetail1 */
	public static final String grecipeDetail1_COLUMN = "GRECIPE_DETAIL1";

	/** COLUMNアノテーション grecipeDetail2 */
	public static final String grecipeDetail2_COLUMN = "GRECIPE_DETAIL2";

	/** COLUMNアノテーション grecipeDetail3 */
	public static final String grecipeDetail3_COLUMN = "GRECIPE_DETAIL3";

	/** COLUMNアノテーション grecipeDetail4 */
	public static final String grecipeDetail4_COLUMN = "GRECIPE_DETAIL4";

	/** COLUMNアノテーション mrecipeDetail1 */
	public static final String mrecipeDetail1_COLUMN = "MRECIPE_DETAIL1";

	/** COLUMNアノテーション mrecipeDetail2 */
	public static final String mrecipeDetail2_COLUMN = "MRECIPE_DETAIL2";

	/** COLUMNアノテーション mrecipeDetail3 */
	public static final String mrecipeDetail3_COLUMN = "MRECIPE_DETAIL3";

	/** COLUMNアノテーション mrecipeDetail4 */
	public static final String mrecipeDetail4_COLUMN = "MRECIPE_DETAIL4";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String grecipeDetail1;

	private String grecipeDetail2;

	private String grecipeDetail3;

	private String grecipeDetail4;

	private String mrecipeDetail1;

	private String mrecipeDetail2;

	private String mrecipeDetail3;

	private String mrecipeDetail4;

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
	 * grecipeDetail1取得.
	 * @return grecipeDetail1
	 */
	public String getGrecipeDetail1() {
		return this.grecipeDetail1;
	}

	/**
	 * grecipeDetail1設定.
	 * @param grecipeDetail1 grecipeDetail1
	 */
	public void setGrecipeDetail1(final String grecipeDetail1) {
		this.grecipeDetail1 = grecipeDetail1;
	}

	/**
	 * grecipeDetail2取得.
	 * @return grecipeDetail2
	 */
	public String getGrecipeDetail2() {
		return this.grecipeDetail2;
	}

	/**
	 * grecipeDetail2設定.
	 * @param grecipeDetail2 grecipeDetail2
	 */
	public void setGrecipeDetail2(final String grecipeDetail2) {
		this.grecipeDetail2 = grecipeDetail2;
	}

	/**
	 * grecipeDetail3取得.
	 * @return grecipeDetail3
	 */
	public String getGrecipeDetail3() {
		return this.grecipeDetail3;
	}

	/**
	 * grecipeDetail3設定.
	 * @param grecipeDetail3 grecipeDetail3
	 */
	public void setGrecipeDetail3(final String grecipeDetail3) {
		this.grecipeDetail3 = grecipeDetail3;
	}

	/**
	 * grecipeDetail4取得.
	 * @return grecipeDetail4
	 */
	public String getGrecipeDetail4() {
		return this.grecipeDetail4;
	}

	/**
	 * grecipeDetail4設定.
	 * @param grecipeDetail4 grecipeDetail4
	 */
	public void setGrecipeDetail4(final String grecipeDetail4) {
		this.grecipeDetail4 = grecipeDetail4;
	}

	/**
	 * mrecipeDetail1取得.
	 * @return mrecipeDetail1
	 */
	public String getMrecipeDetail1() {
		return this.mrecipeDetail1;
	}

	/**
	 * mrecipeDetail1設定.
	 * @param mrecipeDetail1 mrecipeDetail1
	 */
	public void setMrecipeDetail1(final String mrecipeDetail1) {
		this.mrecipeDetail1 = mrecipeDetail1;
	}

	/**
	 * mrecipeDetail2取得.
	 * @return mrecipeDetail2
	 */
	public String getMrecipeDetail2() {
		return this.mrecipeDetail2;
	}

	/**
	 * mrecipeDetail2設定.
	 * @param mrecipeDetail2 mrecipeDetail2
	 */
	public void setMrecipeDetail2(final String mrecipeDetail2) {
		this.mrecipeDetail2 = mrecipeDetail2;
	}

	/**
	 * mrecipeDetail3取得.
	 * @return mrecipeDetail3
	 */
	public String getMrecipeDetail3() {
		return this.mrecipeDetail3;
	}

	/**
	 * mrecipeDetail3設定.
	 * @param mrecipeDetail3 mrecipeDetail3
	 */
	public void setMrecipeDetail3(final String mrecipeDetail3) {
		this.mrecipeDetail3 = mrecipeDetail3;
	}

	/**
	 * mrecipeDetail4取得.
	 * @return mrecipeDetail4
	 */
	public String getMrecipeDetail4() {
		return this.mrecipeDetail4;
	}

	/**
	 * mrecipeDetail4設定.
	 * @param mrecipeDetail4 mrecipeDetail4
	 */
	public void setMrecipeDetail4(final String mrecipeDetail4) {
		this.mrecipeDetail4 = mrecipeDetail4;
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

