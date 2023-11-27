/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoCompleteデータ格納クラス
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_HEADER";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション product */
	public static final String product_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	//
	// インスタンスフィールド
	//

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/** 主要製品コード(品目コード） */
	private String itemCd;

	/** 基本処方名称 */
	private String recipeName;

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
	 * レシピバージョン取得
	 * @return BigDecimal レシピバージョン
	*/
	public BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定
	 * @param recipeVersion レシピバージョン
	*/
	public void setRecipeVersion(final BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 主要製品コード(品目コード）取得
	 * @return String 主要製品コード(品目コード）
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 主要製品コード(品目コード）設定
	 * @param itemCd 主要製品コード(品目コード）
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 基本処方名称取得
	 * @return String 基本処方名称
	*/
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 基本処方名称設定
	 * @param recipeName 基本処方名称
	*/
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

}
