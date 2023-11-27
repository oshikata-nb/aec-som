/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonList;
import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 理由一覧 Formクラス.
 * @author t0011036
 */
public final class ReasonListForm extends AbstractSearchForm {

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

	/* 理由コード */
	private String srhRyCd;

	/* 理由内容 */
	private String srhRyDescription;

	/* 理由リスト */
	private List<ReasonList> searchList;

	/* 帳票Excel用検索条件 */
	private ReasonListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ReasonListForm() {
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
		return ReasonListPagerCondition.class;
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
			setSearchList(new ArrayList<ReasonList>());
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
		setSrhRyCd(null);
		setSrhRyDescription(null);
		setSearchList(new ArrayList<ReasonList>());
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
	public List<ReasonList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ReasonList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhRyCdを取得します。
	 * @return srhRyCd
	 */
	public String getSrhRyCd() {
		return srhRyCd;
	}

	/**
	 * srhRyCdを設定します。
	 * @param srhRyCd srhRyCd
	 */
	public void setSrhRyCd(final String srhRyCd) {
		this.srhRyCd = srhRyCd;
	}

	/**
	 * srhRyDescriptionを取得します。
	 * @return srhRyDescription
	 */
	public String getSrhRyDescription() {
		return srhRyDescription;
	}

	/**
	 * srhRyDescriptionを設定します。
	 * @param srhRyDescription srhRyDescription
	 */
	public void setSrhRyDescription(final String srhRyDescription) {
		this.srhRyDescription = srhRyDescription;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public ReasonListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final ReasonListConditionForReport condition) {
		this.condition = condition;
	}
}
