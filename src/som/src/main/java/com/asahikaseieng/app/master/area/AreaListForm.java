/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.arealist.AreaList;
import com.asahikaseieng.dao.nonentity.master.arealist.AreaListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 地区一覧 Formクラス.
 * @author t0011036
 */
public final class AreaListForm extends AbstractSearchForm {

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

	/* 地区コード */
	private String srhAreaCd;

	/* 地区名称 */
	private String srhAreaName;

	/* 地区リスト */
	private List<AreaList> searchList;

	/* 帳票Excel用検索条件 */
	private AreaListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public AreaListForm() {
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
		return AreaListPagerCondition.class;
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
			setSearchList(new ArrayList<AreaList>());
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
		setSrhAreaCd(null);
		setSrhAreaName(null);
		setSearchList(new ArrayList<AreaList>());
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
	public List<AreaList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<AreaList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhAreaCdを取得します。
	 * @return srhAreaCd
	 */
	public String getSrhAreaCd() {
		return srhAreaCd;
	}

	/**
	 * srhAreaCdを設定します。
	 * @param srhAreaCd srhAreaCd
	 */
	public void setSrhAreaCd(final String srhAreaCd) {
		this.srhAreaCd = srhAreaCd;
	}

	/**
	 * srhAreaNameを取得します。
	 * @return srhAreaName
	 */
	public String getSrhAreaName() {
		return srhAreaName;
	}

	/**
	 * srhAreaNameを設定します。
	 * @param srhAreaName srhAreaName
	 */
	public void setSrhAreaName(final String srhAreaName) {
		this.srhAreaName = srhAreaName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public AreaListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final AreaListConditionForReport condition) {
		this.condition = condition;
	}
}
