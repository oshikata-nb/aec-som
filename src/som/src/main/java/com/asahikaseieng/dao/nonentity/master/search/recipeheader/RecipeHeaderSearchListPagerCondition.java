/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.master.search.recipeheader;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 基本処方検索Popup－ 一覧複数ページ制御クラス.
 *
 * @author tosco
 */
public class RecipeHeaderSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderSearchListPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：レシピコード */
	private String recipeCd;
	/** 検索入力：基本処方名称 */
	private String recipeName;
	/** 検索入力：主要製品コード(品目コード） */
	private String itemCd;
	/** 検索入力：自社・花王区分 */
	private String shipperDivision;
	/** 検索入力：他社コード１ */
	private String otherCompanyCd1;
	/** 検索入力：PR生産ライン */
	private String productionLine;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.レシピコード取得
	 * @return recipeCd
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * 検索入力.レシピコード設定
	 * * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = AecTextUtils.likeFilter(recipeCd);
	}

	/**
	 * 検索入力.生産工場
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 検索入力.生産工場
	 * * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 検索入力.主要製品コード(品目コード）取得
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 検索入力.主要製品コード(品目コード）設定
	 * * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = AecTextUtils.likeFilter(itemCd);
	}

	/**
	 * 検索入力.基本処方名称取得
	 * @return recipeName
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 検索入力.基本処方名称設定
	 * * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = AecTextUtils.likeFilter(recipeName);
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
	 * 検索入力：自社・花王区分を取得します。
	 * @return 検索入力：自社・花王区分
	 */
	public String getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 検索入力：自社・花王区分を設定します。
	 * @param shipperDivision 検索入力：自社・花王区分
	 */
	public void setShipperDivision(final String shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

}
