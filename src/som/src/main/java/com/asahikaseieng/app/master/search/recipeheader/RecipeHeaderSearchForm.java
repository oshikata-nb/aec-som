/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.recipeheader;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchList;
import com.asahikaseieng.dao.nonentity.master.search.recipeheader.RecipeHeaderSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 基本処方検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class RecipeHeaderSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 基本処方コード */
	private String recipeCd;

	/** 基本処方名 */
	private String recipeName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 自社／花王区分 */
	private String shipperDivision;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 生産工場 */
	private String productLine;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** 検索結果リスト */
	private List<RecipeHeaderSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderSearchForm() {
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
	protected Class<RecipeHeaderSearchListPagerCondition> getPagerConditionClass() {
		return RecipeHeaderSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			setSearchList(new ArrayList<RecipeHeaderSearchList>());
			clear();
		}
	}

	/**
	 * クリア
	 */
	public void clear() {
		setRecipeCd(null);
		setRecipeName(null);
		setItemCd(null);
		setItemName(null);
		setShipperDivision(null);
		setOtherCompanyCd1(null);
		setProductLine(null);
		setLineCombo(null);
	}

	/**
	 * 品目コードを取得します。
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称を取得します。
	 * @return String 品目名称
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
	 * 他社コード1を取得します。
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1を設定します。
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<RecipeHeaderSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<RecipeHeaderSearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 基本処方コードを取得します。
	 * @return recipeCd
	 */
	public String getRecipeCd() {
		return recipeCd;
	}

	/**
	 * 基本処方コードを設定します。
	 * @param recipeCd 基本処方コード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * 基本処方名を取得します。
	 * @return 基本処方名
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名を設定します。
	 * @param recipeName 基本処方名
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 自社／花王区分を取得します。
	 * @return 自社／花王区分
	 */
	public String getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 自社／花王区分を設定します。
	 * @param shipperDivision 自社／花王区分
	 */
	public void setShipperDivision(final String shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 生産工場を取得します。
	 * @return 生産工場
	 */
	public String getProductLine() {
		return productLine;
	}

	/**
	 * 生産工場を設定します。
	 * @param productLine 生産工場
	 */
	public void setProductLine(final String productLine) {
		this.productLine = productLine;
	}

	/**
	 * 生産工場コンボボックスを取得します。
	 * @return 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 生産工場コンボボックスを設定します。
	 * @param lineCombo 生産工場コンボボックス
	 */
	public void setLineCombo(final List<ComboBoxItems> lineCombo) {
		this.lineCombo = lineCombo;
	}
}
