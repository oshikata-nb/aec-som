/*
 * Created on 2009/01/15
 *
 * $copyright$
 * 基本処方-翻訳検索
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeTranslateListPagerCondition;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 基本処方-翻訳検索 Formクラス.
 * @author tosco
 * 
 */
public final class MgrecipeTranslateListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.翻訳検索
	//

	/** 検索入力：レシピコード */
	private String recipeCd;

	/** 検索入力：レシピバージョン */
	private String recipeVersion;

	/** 検索入力：ステータス */
	private String recipeStatus;

	/** 検索入力：承認ステータス */
	private String approvalStatus;

	/** 検索入力：用途 */
	private String recipeUse;

	/** 検索入力：主要製品コード(品目コード） */
	private String product;

	/** 検索入力：他社コード１ */
	private String otherCompanyCd1;

	/** 検索入力：品目名称 */
	private String itemName;

	/** 検索入力：最新バージョン */
	private boolean latestVersion;

	/** 検索入力：未展開 */
	private boolean isExplicate;

	/** 検索結果リスト */
	private List<RecipeHeaderList> searchList;

	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/**
	 * コンストラクタ.翻訳検索
	 */
	public MgrecipeTranslateListForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class<MgrecipeTranslateListPagerCondition> getPagerConditionClass() {
		return MgrecipeTranslateListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
			// ステータスコンボボックスをクリア
			setStatusCombo(null);
		}

		// チェックボックスをクリア
		setLatestVersion(false);
		setExplicate(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<RecipeHeaderList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.翻訳検索
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<RecipeHeaderList>());

		/** 検索入力：レシピコード */
		setRecipeCd(null);
		/** 検索入力：レシピバージョン */
		setRecipeVersion(null);
		/** 検索入力：ステータス */
		setRecipeStatus(null);
		/** 検索入力：承認ステータス */
		setApprovalStatus(null);
		/** 検索入力：1:Planning,2:Costing,3:製造,4:包装 */
		setRecipeUse(null);
		/** 検索入力：主要製品コード(品目コード） */
		setProduct(null);
		/** 検索入力：基本処方名称 */
		setOtherCompanyCd1(null);
		/** 検索入力：品目名称 */
		setItemName(null);

	}

	/**
	 * 翻訳検索：searchListを取得します。
	 * @return searchList
	 */
	public List<RecipeHeaderList> getSearchList() {
		return searchList;
	}

	/**
	 * 翻訳検索： searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<RecipeHeaderList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.翻訳検索
	//

	/**
	 * 検索入力：レシピコード取得.
	 * @return String レシピコード
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * 検索入力：レシピコード設定.
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * 検索入力：レシピバージョン取得.
	 * @return String レシピバージョン
	 */
	public String getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * 検索入力：レシピバージョン設定.
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final String recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 検索入力：ステータス
	 * @return String ステータス
	 */
	public String getRecipeStatus() {
		return this.recipeStatus;
	}

	/**
	 * 検索入力：ステータス
	 * @param recipeStatus ステータス
	 */
	public void setRecipeStatus(final String recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * 検索入力：用途.
	 * @return String 用途
	 */
	public String getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * 検索入力：用途
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * 検索入力：主要製品コード(品目コード）取得.
	 * @return String 主要製品コード(品目コード）
	 */
	public String getProduct() {
		return this.product;
	}

	/**
	 * 検索入力：主要製品コード(品目コード）設定.
	 * @param product 主要製品コード(品目コード）
	 */
	public void setProduct(final String product) {
		this.product = product;
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
	 * 検索入力：他社コード１を取得します。
	 * @return 検索入力：他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１を設定します。
	 * @param otherCompanyCd1 検索入力：他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 検索入力：品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 検索入力：品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 検索結果数を取得する。
	 * @return 検索結果数
	 */
	public int getCount() {
		int res = 0;
		if (searchList != null) {
			res = searchList.size();
		}
		return res;
	}

	/**
	 * ステータスコンボボックスを取得します。
	 * @return ステータスコンボボックス
	 */
	public List<ComboBoxItems> getStatusCombo() {
		return statusCombo;
	}

	/**
	 * ステータスコンボボックスを設定します。
	 * @param statusCombo ステータスコンボボックス
	 */
	public void setStatusCombo(final List<ComboBoxItems> statusCombo) {
		this.statusCombo = statusCombo;
	}

	/**
	 * 検索入力：未展開を取得します。
	 * @return 検索入力：未展開
	 */
	public boolean isExplicate() {
		return isExplicate;
	}

	/**
	 * 検索入力：未展開を設定します。
	 * @param isExplicate 検索入力：未展開
	 */
	public void setExplicate(final boolean isExplicate) {
		this.isExplicate = isExplicate;
	}

	/**
	 * 検索入力：最新バージョンを取得します。
	 * @return 検索入力：最新バージョン
	 */
	public boolean isLatestVersion() {
		return latestVersion;
	}

	/**
	 * 検索入力：最新バージョンを設定します。
	 * @param latestVersion 検索入力：最新バージョン
	 */
	public void setLatestVersion(final boolean latestVersion) {
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
