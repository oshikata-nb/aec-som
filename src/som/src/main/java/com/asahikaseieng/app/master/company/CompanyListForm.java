/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyList;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 自社一覧 Formクラス.
 * @author t0011036
 */
public final class CompanyListForm extends AbstractSearchForm {

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

	/* 自社名称1 */
	private String srhHomeName1;

	/* 自社リスト */
	private List<CompanyList> searchList;

	/* 帳票Excel用検索条件 */
	private CompanyListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public CompanyListForm() {
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
		return CompanyListPagerCondition.class;
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
			setSearchList(new ArrayList<CompanyList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhHomeName1(null);
		setSearchList(new ArrayList<CompanyList>());
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
	public List<CompanyList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<CompanyList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhHomeName1を取得します。
	 * @return srhHomeName1
	 */
	public String getSrhHomeName1() {
		return srhHomeName1;
	}

	/**
	 * srhHomeName1を設定します。
	 * @param srhHomeName1 srhHomeName1
	 */
	public void setSrhHomeName1(final String srhHomeName1) {
		this.srhHomeName1 = srhHomeName1;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public CompanyListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final CompanyListConditionForReport condition) {
		this.condition = condition;
	}
}
