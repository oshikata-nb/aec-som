/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentList;
import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 成分一覧 Formクラス.
 * @author t0011036
 */
public final class ComponentListForm extends AbstractSearchForm {

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

	/* 成分コード */
	private String srhComponentCd;

	/* 成分名称 */
	private String srhComponentName;

	/* 成分リスト */
	private List<ComponentList> searchList;

	/* 帳票Excel用検索条件 */
	private ComponentListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ComponentListForm() {
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
		return ComponentListPagerCondition.class;
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
			setSearchList(new ArrayList<ComponentList>());
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
		setSrhComponentCd(null);
		setSrhComponentName(null);
		setSearchList(new ArrayList<ComponentList>());
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
	public List<ComponentList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ComponentList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhComponentCdを取得します。
	 * @return srhComponentCd
	 */
	public String getSrhComponentCd() {
		return srhComponentCd;
	}

	/**
	 * srhComponentCdを設定します。
	 * @param srhComponentCd srhComponentCd
	 */
	public void setSrhComponentCd(final String srhComponentCd) {
		this.srhComponentCd = srhComponentCd;
	}

	/**
	 * srhComponentNameを取得します。
	 * @return srhComponentName
	 */
	public String getSrhComponentName() {
		return srhComponentName;
	}

	/**
	 * srhComponentNameを設定します。
	 * @param srhComponentName srhComponentName
	 */
	public void setSrhComponentName(final String srhComponentName) {
		this.srhComponentName = srhComponentName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ComponentListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ComponentListConditionForReport condition) {
		this.condition = condition;
	}
}
