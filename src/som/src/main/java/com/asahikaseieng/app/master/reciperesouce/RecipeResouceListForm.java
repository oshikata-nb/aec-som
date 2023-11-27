/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 設備一覧 Formクラス.
 * @author t0011036
 */
public final class RecipeResouceListForm extends AbstractSearchForm {

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

	/* 設備コード */
	private String srhResouceCd;

	/* 設備名称 */
	private String srhResouceName;

	/* 設備リスト */
	private List<RecipeResouceList> searchList;

	/* 帳票Excel用検索条件 */
	private RecipeResouceListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceListForm() {
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
		return RecipeResouceListPagerCondition.class;
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
			setSearchList(new ArrayList<RecipeResouceList>());
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
		setSrhResouceCd(null);
		setSrhResouceName(null);
		setSearchList(new ArrayList<RecipeResouceList>());
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
	 * srhResouceCdを取得します。
	 * @return srhResouceCd
	 */
	public String getSrhResouceCd() {
		return srhResouceCd;
	}

	/**
	 * srhResouceCdを設定します。
	 * @param srhResouceCd srhResouceCd
	 */
	public void setSrhResouceCd(final String srhResouceCd) {
		this.srhResouceCd = srhResouceCd;
	}

	/**
	 * srhResouceNameを取得します。
	 * @return srhResouceName
	 */
	public String getSrhResouceName() {
		return srhResouceName;
	}

	/**
	 * srhResouceNameを設定します。
	 * @param srhResouceName srhResouceName
	 */
	public void setSrhResouceName(final String srhResouceName) {
		this.srhResouceName = srhResouceName;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<RecipeResouceList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<RecipeResouceList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public RecipeResouceListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final RecipeResouceListConditionForReport condition) {
		this.condition = condition;
	}
}
