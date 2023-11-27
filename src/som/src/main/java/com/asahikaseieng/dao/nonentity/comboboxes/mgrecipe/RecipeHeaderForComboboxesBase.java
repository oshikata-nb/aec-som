/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 基本処方－工程パターンコンボボックス用データ
 *
 * @author tosco
 */
public class RecipeHeaderForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_HEADER";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeDescription */
	public static final String recipeDescription_COLUMN = "RECIPE_DESCRIPTION";

	//
	// インスタンスフィールド
	//

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** 注釈 */
	private String recipeDescription;

	//
	// インスタンスメソッド
	//

	/**
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param recipeId レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得
	 * @return String レシピコード
	*/
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定
	 * @param recipeCd レシピコード
	*/
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	*/
	public String getRecipeDescription() {
		return this.recipeDescription;
	}

	/**
	 * 注釈設定
	 * @param recipeDescription 注釈
	*/
	public void setRecipeDescription(final String recipeDescription) {
		this.recipeDescription = recipeDescription;
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
