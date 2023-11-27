/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 翻訳検索 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class MgrecipeTranslateListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeTranslateListPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：レシピコード */
	private String recipeCd;

	/** 検索入力：レシピバージョン */
	private String recipeVersion;

	/** 検索入力：ステータス */
	private String recipeStatus;

	/** 検索入力：承認ステータス */
	private String approvalStatus;

	/** 検索入力：1:Planning,2:Costing,3:製造,4:包装 */
	private String recipeUse;

	/** 検索入力：主要製品コード(品目コード） */
	private String product;

	/** 検索入力：他社コード１ */
	private String otherCompanyCd1;

	/** 検索入力：最新バージョン */
	private String latestVersion;

	/** 検索入力：未展開 */
	private String explicate;

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
	 * 検索入力：未展開を取得します。
	 * @return 検索入力：未展開
	 */
	public String getExplicate() {
		return explicate;
	}

	/**
	 * 検索入力：未展開を設定します。
	 * @param explicate 検索入力：未展開
	 */
	public void setExplicate(final String explicate) {
		this.explicate = explicate;
	}

	/**
	 * 検索入力：最新バージョンを取得します。
	 * @return 検索入力：最新バージョン
	 */
	public String getLatestVersion() {
		return latestVersion;
	}

	/**
	 * 検索入力：最新バージョンを設定します。
	 * @param latestVersion 検索入力：最新バージョン
	 */
	public void setLatestVersion(final String latestVersion) {
		this.latestVersion = latestVersion;
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
