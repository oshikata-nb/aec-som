/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryList;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 品目分類一覧 Formクラス.
 * @author t0011036
 */
public final class ItemCategoryListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

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

	/* 品目分類コード */
	private String srhItemCategory;

	/* 品目分類名称 */
	private String srhItemCategoryName;

	/* 品目分類リスト */
	private List<ItemCategoryList> searchList;

	/* 帳票Excel用検索条件 */
	private ItemCategoryListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryListForm() {
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
	protected Class getPagerConditionClass() {
		return ItemCategoryListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
		}

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ItemCategoryList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhItemCategory(null);
		setSrhItemCategoryName(null);
		setSearchList(new ArrayList<ItemCategoryList>());
		setCondition(null);
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<ItemCategoryList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ItemCategoryList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhItemCategoryを取得します。
	 * @return srhItemCategory
	 */
	public String getSrhItemCategory() {
		return srhItemCategory;
	}

	/**
	 * srhItemCategoryを設定します。
	 * @param srhItemCategory srhItemCategory
	 */
	public void setSrhItemCategory(final String srhItemCategory) {
		this.srhItemCategory = srhItemCategory;
	}

	/**
	 * srhItemCategoryNameを取得します。
	 * @return srhItemCategoryName
	 */
	public String getSrhItemCategoryName() {
		return srhItemCategoryName;
	}

	/**
	 * srhItemCategoryNameを設定します。
	 * @param srhItemCategoryName srhItemCategoryName
	 */
	public void setSrhItemCategoryName(final String srhItemCategoryName) {
		this.srhItemCategoryName = srhItemCategoryName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ItemCategoryListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ItemCategoryListConditionForReport condition) {
		this.condition = condition;
	}
}
