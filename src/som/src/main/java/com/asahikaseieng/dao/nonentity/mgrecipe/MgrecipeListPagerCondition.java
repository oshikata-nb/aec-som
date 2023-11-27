/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 基本処方検索 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class MgrecipeListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeListPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：レシピコード */
	private String recipeCd;

	/** 検索入力：レシピバージョン */
	private String recipeVersion;

	/** 検索入力：0..ラボ使用 1..試作用 2..一般使用 3..廃棄 4..改訂中 5..保留中 */
	private String recipeStatus;

	/** 検索入力：承認ステータス */
	private String approvalStatus;

	/** 検索入力：1:Planning,2:Costing,3:製造,4:包装 */
	private String recipeUse;

	/** 検索入力：PR生産ライン */
	private String productionLine;

	/** 検索入力：主要製品コード(品目コード） */
	private String product;

	/** 検索入力：基本処方名称 */
	private String recipeName;

	/** 検索入力：他社コード１ */
	private String otherCompanyCd1;

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
	 * 検索入力.レシピコード設定 *
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = AecTextUtils.likeFilter(recipeCd);
	}

	/**
	 * 検索入力.レシピバージョン取得
	 * @return recipeVersion
	 */
	public String getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * 検索入力.レシピバージョン設定 *
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final String recipeVersion) {
		this.recipeVersion = AecTextUtils.likeFilter(recipeVersion);
	}

	/**
	 * 検索入力.ステータス
	 * @return recipeStatus
	 */
	public String getRecipeStatus() {
		return this.recipeStatus;
	}

	/**
	 * 検索入力.ステータス *
	 * @param recipeStatus ステータス
	 */
	public void setRecipeStatus(final String recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * 検索入力.用途
	 * @return recipeUse 用途
	 */
	public String getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * 検索入力.用途 *
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * 検索入力.生産工場
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 検索入力.生産工場 *
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 検索入力.主要製品コード(品目コード）取得
	 * @return product
	 */
	public String getProduct() {
		return this.product;
	}

	/**
	 * 検索入力.主要製品コード(品目コード）設定 *
	 * @param product 品目コード
	 */
	public void setProduct(final String product) {
		this.product = AecTextUtils.likeFilter(product);
	}

	/**
	 * 検索入力.基本処方名称取得
	 * @return recipeName
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 検索入力.基本処方名称設定 *
	 * @param recipeName 基本処方名称
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
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
