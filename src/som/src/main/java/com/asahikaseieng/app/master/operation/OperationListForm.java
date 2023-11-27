/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.operationlist.OperationList;
import com.asahikaseieng.dao.nonentity.master.operationlist.OperationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 工程一覧 Formクラス.
 * @author t0011036
 */
public final class OperationListForm extends AbstractSearchForm {

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

	/* 工程コード */
	private String srhOperationCd;

	/* 工程名称 */
	private String srhOperationName;

	/* 工程リスト */
	private List<OperationList> searchList;

	/* 帳票Excel用検索条件 */
	private OperationListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public OperationListForm() {
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
		return OperationListPagerCondition.class;
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
			setSearchList(new ArrayList<OperationList>());
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
		setSrhOperationCd(null);
		setSrhOperationName(null);
		setSearchList(new ArrayList<OperationList>());
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
	public List<OperationList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<OperationList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhOperationCdを取得します。
	 * @return srhOperationCd
	 */
	public String getSrhOperationCd() {
		return srhOperationCd;
	}

	/**
	 * srhOperationCdを設定します。
	 * @param srhOperationCd srhOperationCd
	 */
	public void setSrhOperationCd(final String srhOperationCd) {
		this.srhOperationCd = srhOperationCd;
	}

	/**
	 * srhOperationNameを取得します。
	 * @return srhOperationName
	 */
	public String getSrhOperationName() {
		return srhOperationName;
	}

	/**
	 * srhOperationNameを設定します。
	 * @param srhOperationName srhOperationName
	 */
	public void setSrhOperationName(final String srhOperationName) {
		this.srhOperationName = srhOperationName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public OperationListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final OperationListConditionForReport condition) {
		this.condition = condition;
	}
}
