/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 基本処方-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractMgrecipeForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ * */
	private String dirtyFlg;

	// インスタンスフィールド

	/** レシピインデックス */
	private String recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private String recipeVersion;

	/** 基本処方名称 */
	private String recipeName;

	/** 品目コード */
	private String product;

	/** 品目名称 */
	private String itemName;

	/** 生産工場 */
	private String productionLine;

	/** 生産工場名称 */
	private String productionLineName;

	/** 用途 */
	private String recipeUse;

	/** 用途名 */
	private String recipeUseName;

	/** タブID */
	private String tabId;

	/** 登録者ID */
	private String inputorCd;

	/** 登録日時 */
	private String inputDate;

	/** 更新者ID */
	private String updatorCd;

	/** 更新日時 */
	private String updateDate;

	/** ステータス */
	private String recipeStatus;

	/** 承認ステータス 1:入力中 2:承認依頼中 3:承認済み */
	private String approvalStatus;

	/** 承認ステータス名称 */
	private String approvalStatusName;

	/** 原処方レシピインデックス */
	private String originalRecipeId;

	/** 荷姿 */
	private String styleOfPacking;

	/**
	 * クリア処理.
	 */
	protected void clear() {
		// レシピインデックス
		setRecipeId(null);
		// レシピコード
		setRecipeCd(null);
		// レシピバージョン
		setRecipeVersion(null);
		// 用途
		setRecipeUse(null);
		// PR生産ライン
		setProductionLine(null);
		// 主要製品コード(品目コード）
		setProduct(null);
		// 基本処方名称
		setRecipeName(null);
		// 品目名
		setItemName(null);
		// 用途名
		setRecipeUseName(null);
		// タブID
		setTabId(null);
		// 生産工場
		setProductionLineName(null);
		// 登録者ID
		setInputorCd(null);
		// 登録日時
		setInputDate(null);
		// 更新者ID
		setUpdatorCd(null);
		// 更新日時
		setUpdateDate(null);
		// ステータス
		setRecipeStatus(null);
		// 承認ステータス 1:入力中 2:承認依頼中 3:承認済み
		setApprovalStatus(null);
		// 承認ステータス名称
		setApprovalStatusName(null);
		// 原処方レシピインデックス
		setOriginalRecipeId(null);
		// 荷姿
		setStyleOfPacking(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * レシピインデックス取得.
	 * @return String レシピインデックス
	 */
	public String getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定.
	 * @param recipeId レシピインデックス
	 */
	public void setRecipeId(final String recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得.
	 * @return String レシピコード
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * レシピコード設定.
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * レシピバージョン取得.
	 * @return String レシピバージョン
	 */
	public String getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * レシピバージョン設定.
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final String recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 1:Planning,2:Costing,3:製造,4:包装取得.
	 * @return String 1:Planning,2:Costing,3:製造,4:包装
	 */
	public String getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * 1:Planning,2:Costing,3:製造,4:包装設定.
	 * @param recipeUse 1:Planning,2:Costing,3:製造,4:包装
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * PR生産ライン取得.
	 * @return String PR生産ライン
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * PR生産ライン設定.
	 * @param productionLine PR生産ライン
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 主要製品コード(品目コード）取得.
	 * @return String 主要製品コード(品目コード）
	 */
	public String getProduct() {
		return this.product;
	}

	/**
	 * 主要製品コード(品目コード）設定.
	 * @param product 主要製品コード(品目コード）
	 */
	public void setProduct(final String product) {
		this.product = product;
	}

	/**
	 * 基本処方名称取得.
	 * @return String 基本処方名称
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * 基本処方名称設定.
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 用途名を取得します。
	 * @return 用途名
	 */
	public String getRecipeUseName() {
		return recipeUseName;
	}

	/**
	 * 用途名を設定します。
	 * @param recipeUseName 用途名
	 */
	public void setRecipeUseName(final String recipeUseName) {
		this.recipeUseName = recipeUseName;
	}

	/**
	 * タブIDを取得します。
	 * @return タブID
	 */
	public String getTabId() {
		return tabId;
	}

	/**
	 * タブIDを設定します。
	 * @param tabId タブID
	 */
	public void setTabId(final String tabId) {
		this.tabId = tabId;
	}

	/**
	 * 生産工場名称を取得します。
	 * @return 生産工場名称
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産工場名称を設定します。
	 * @param productionLineName 生産工場名称
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	 */
	public String getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public String getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * ステータス取得.
	 * @return String ステータス
	 */
	public String getRecipeStatus() {
		return this.recipeStatus;
	}

	/**
	 * ステータス設定.
	 * @param recipeStatus ステータス
	 */
	public void setRecipeStatus(final String recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * 承認ステータス 1:入力中 2:承認依頼中 3:承認済み取得.
	 * @return String 承認ステータス 1:入力中 2:承認依頼中 3:承認済み
	 */
	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * 承認ステータス 1:入力中 2:承認依頼中 3:承認済み設定.
	 * @param approvalStatus 承認ステータス 1:入力中 2:承認依頼中 3:承認済み
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 原処方レシピインデックスを取得します。
	 * @return 原処方レシピインデックス
	 */
	public String getOriginalRecipeId() {
		return originalRecipeId;
	}

	/**
	 * 原処方レシピインデックスを設定します。
	 * @param originalRecipeId 原処方レシピインデックス
	 */
	public void setOriginalRecipeId(final String originalRecipeId) {
		this.originalRecipeId = originalRecipeId;
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
	 * approvalStatusNameを取得します。
	 * @return approvalStatusName
	 */
	public String getApprovalStatusName() {
		return approvalStatusName;
	}

	/**
	 * approvalStatusNameを設定します。
	 * @param approvalStatusName approvalStatusName
	 */
	public void setApprovalStatusName(final String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}
}
