/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 設備グループ一覧 Formクラス.
 * @author t0011036
 */
public final class RecipeResouceGroupListForm extends AbstractSearchForm {

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

	/* 設備グループコード */
	private String srhResouceGroupCd;

	/* 設備グループ名称 */
	private String srhResouceGroupName;

	/* 設備グループリスト */
	private List<RecipeResouceGroupList> searchList;

	/* 帳票Excel用検索条件 */
	private RecipeResouceGroupListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupListForm() {
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
		return RecipeResouceGroupListPagerCondition.class;
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
			setSearchList(new ArrayList<RecipeResouceGroupList>());
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
		setSrhResouceGroupCd(null);
		setSrhResouceGroupName(null);
		setSearchList(new ArrayList<RecipeResouceGroupList>());
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
	public List<RecipeResouceGroupList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<RecipeResouceGroupList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhResouceGroupCdを取得します。
	 * @return srhResouceGroupCd
	 */
	public String getSrhResouceGroupCd() {
		return srhResouceGroupCd;
	}

	/**
	 * srhResouceGroupCdを設定します。
	 * @param srhResouceGroupCd srhResouceGroupCd
	 */
	public void setSrhResouceGroupCd(final String srhResouceGroupCd) {
		this.srhResouceGroupCd = srhResouceGroupCd;
	}

	/**
	 * srhResouceGroupNameを取得します。
	 * @return srhResouceGroupName
	 */
	public String getSrhResouceGroupName() {
		return srhResouceGroupName;
	}

	/**
	 * srhResouceGroupNameを設定します。
	 * @param srhResouceGroupName srhResouceGroupName
	 */
	public void setSrhResouceGroupName(final String srhResouceGroupName) {
		this.srhResouceGroupName = srhResouceGroupName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public RecipeResouceGroupListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(
			final RecipeResouceGroupListConditionForReport condition) {
		this.condition = condition;
	}
}
