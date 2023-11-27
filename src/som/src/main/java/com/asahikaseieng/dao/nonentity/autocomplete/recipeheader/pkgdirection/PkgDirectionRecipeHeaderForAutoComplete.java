/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection;

/**
 * 包装指図－基本処方ヘッダ情報AutoCompleteデータ格納クラス
 * @author tosco
 */
public class PkgDirectionRecipeHeaderForAutoComplete extends PkgDirectionRecipeHeaderForAutoCompleteBase {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション recipeCdVersion */
	public static final String recipeCdVersion_COLUMN = "RECIPE_CD_VERSION";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** レシピコードバージョン **/
	private String recipeCdVersion;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 名称１ */
	private String name01;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionRecipeHeaderForAutoComplete() {
		super();
	}

	/**
	 * レシピコードバージョン取得.
	 * @return レシピコードバージョン
	 */
	public String getRecipeCdVersion() {
		return this.recipeCdVersion;
	}

	/**
	 * レシピコードバージョン設定.
	 * @param recipeCdVersion レシピコードバージョン
	 */
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
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
	 * 他社コード１取得.
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１設定.
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿取得.
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定.
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
