/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 包装指図－基本処方ヘッダ情報AutoComplete結果表示用Bean
 * @author tosco
 */
public class PkgDirectionRecipeHeaderForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** レシピコードバージョン */
	private String recipeCdVersion;

	/** 生産ライン */
	private String productionLine;

	/** 主要製品コード(品目コード） */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 名称１ */
	private String name01;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteBean(final String code, final String name) {
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
	 * レシピコードバージョン取得
	 * @return String レシピコードバージョン
	*/
	public String getRecipeCdVersion() {
		return this.recipeCdVersion;
	}

	/**
	 * レシピコードバージョン設定
	 * @param recipeCdVersion レシピコードバージョン
	*/
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
	}

	/**
	 * 生産ライン取得
	 * @return String 生産ライン
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定
	 * @param productionLine 生産ライン
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
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
	 * 品目名称取得.
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード１を取得します。
	 * @return 他社コード１
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
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 名称１取得
	 * @return 名称１
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * 名称１設定
	 * @param name01 名称１
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}
}
