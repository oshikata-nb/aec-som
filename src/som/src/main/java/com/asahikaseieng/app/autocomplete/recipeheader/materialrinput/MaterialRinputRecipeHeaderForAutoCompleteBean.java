/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.materialrinput;

import java.math.BigDecimal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete結果表示用Bean
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private BigDecimal recipeVersion;

	/**
	 * コンストラクタ
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

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

}
