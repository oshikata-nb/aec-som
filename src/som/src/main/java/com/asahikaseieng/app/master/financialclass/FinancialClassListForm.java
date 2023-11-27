/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassList;
import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 財務分類一覧 Formクラス.
 * @author t0011036
 */
public final class FinancialClassListForm extends AbstractSearchForm {

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

	/* 財務分類コード */
	private String srhFinancialClassCd;

	/* 財務分類名称 */
	private String srhFinancialClassName;

	/* 財務分類リスト */
	private List<FinancialClassList> searchList;

	/* 帳票Excel用検索条件 */
	private FinancialClassListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassListForm() {
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
		return FinancialClassListPagerCondition.class;
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
			setSearchList(new ArrayList<FinancialClassList>());
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
		setSrhFinancialClassCd(null);
		setSrhFinancialClassName(null);
		setSearchList(new ArrayList<FinancialClassList>());
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
	public List<FinancialClassList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<FinancialClassList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhFinancialClassCdを取得します。
	 * @return srhFinancialClassCd
	 */
	public String getSrhFinancialClassCd() {
		return srhFinancialClassCd;
	}

	/**
	 * srhFinancialClassCdを設定します。
	 * @param srhFinancialClassCd srhFinancialClassCd
	 */
	public void setSrhFinancialClassCd(final String srhFinancialClassCd) {
		this.srhFinancialClassCd = srhFinancialClassCd;
	}

	/**
	 * srhFinancialClassNameを取得します。
	 * @return srhFinancialClassName
	 */
	public String getSrhFinancialClassName() {
		return srhFinancialClassName;
	}

	/**
	 * srhFinancialClassNameを設定します。
	 * @param srhFinancialClassName srhFinancialClassName
	 */
	public void setSrhFinancialClassName(final String srhFinancialClassName) {
		this.srhFinancialClassName = srhFinancialClassName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public FinancialClassListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(
			final FinancialClassListConditionForReport condition) {
		this.condition = condition;
	}
}
