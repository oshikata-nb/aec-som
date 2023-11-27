/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.line;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.linelist.LineList;
import com.asahikaseieng.dao.nonentity.master.linelist.LineListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 生産ライン一覧 Formクラス.
 * @author t0011036
 */
public final class LineListForm extends AbstractSearchForm {

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

	/* 生産ラインコード */
	private String srhProductionLine;

	/* 生産ライン名称 */
	private String srhProductionLineName;

	/* 生産ラインリスト */
	private List<LineList> searchList;

	/* 帳票Excel用検索条件 */
	private LineListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public LineListForm() {
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
		return LineListPagerCondition.class;
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
			setSearchList(new ArrayList<LineList>());
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
		setSrhProductionLine(null);
		setSrhProductionLineName(null);
		setSearchList(new ArrayList<LineList>());
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
	 * srhProductionLineを取得します。
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return srhProductionLine;
	}

	/**
	 * srhProductionLineを設定します。
	 * @param srhProductionLine srhProductionLine
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * srhProductionLineNameを取得します。
	 * @return srhProductionLineName
	 */
	public String getSrhProductionLineName() {
		return srhProductionLineName;
	}

	/**
	 * srhProductionLineNameを設定します。
	 * @param srhProductionLineName srhProductionLineName
	 */
	public void setSrhProductionLineName(final String srhProductionLineName) {
		this.srhProductionLineName = srhProductionLineName;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<LineList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<LineList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public LineListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final LineListConditionForReport condition) {
		this.condition = condition;
	}
}
