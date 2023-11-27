/*
 * Created on 2008/07/17
 *
 * $copyright$
 */
package com.asahikaseieng.app.master.cal;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.callist.CalList;
import com.asahikaseieng.dao.nonentity.master.callist.CalListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * カレンダー一覧 Formクラス.
 * @author tosco
 */
public final class CalListForm extends AbstractSearchForm {

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

	/* カレンダーコード */
	private String srhCalCd;

	/* 検索入力：カレンダー用途名 */
	private String srhCalName;

	/* 検索入力：会計年度 */
	private String srhCalYear;

	/* リスト */
	private List<CalList> searchList;

	/* 帳票Excel用検索条件 */
	private CalListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public CalListForm() {
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
		return CalListPagerCondition.class;
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
			setSearchList(new ArrayList<CalList>());
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
		setSrhCalCd(null);
		setSrhCalName(null);
		setSrhCalYear(null);
		setSearchList(new ArrayList<CalList>());
		setCondition(null);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<CalList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<CalList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhCalCdを取得します。
	 * @return srhCalCd
	 */
	public String getSrhCalCd() {
		return srhCalCd;
	}

	/**
	 * srhCalCdを設定します。
	 * @param srhCalCd srhCalCd
	 */
	public void setSrhCalCd(final String srhCalCd) {
		this.srhCalCd = srhCalCd;
	}

	/**
	 * srhCalNameを取得します。
	 * @return srhCalName
	 */
	public String getSrhCalName() {
		return srhCalName;
	}

	/**
	 * srhCalNameを設定します。
	 * @param srhCalName srhCalName
	 */
	public void setSrhCalName(final String srhCalName) {
		this.srhCalName = srhCalName;
	}

	/**
	 * srhCalYearを取得します。
	 * @return srhCalYear
	 */
	public String getSrhCalYear() {
		return srhCalYear;
	}

	/**
	 * srhCalYearを設定します。
	 * @param srhCalYear srhCalYear
	 */
	public void setSrhCalYear(final String srhCalYear) {
		this.srhCalYear = srhCalYear;
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
	 * conditionを取得します。
	 * @return condition
	 */
	public CalListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final CalListConditionForReport condition) {
		this.condition = condition;
	}
}
