/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.nameslist.NamesList;
import com.asahikaseieng.dao.nonentity.master.nameslist.NamesListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 名称一覧 Formクラス.
 * @author t0011036
 */
public final class NamesListForm extends AbstractSearchForm {

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

	/* 名称区分 */
	private String srhNameDivision;

	private String nameDivision;

	/* 名称コード */
	private String srhNameCd;

	/* 名称1 */
	private String srhName01;

	/* 名称リスト */
	private List<NamesList> searchList;

	/* 帳票Excel用検索条件 */
	private NamesListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public NamesListForm() {
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
		return NamesListPagerCondition.class;
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
			setSearchList(new ArrayList<NamesList>());
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
		setSrhNameDivision(null);
		setNameDivision("UNIT");
		setSrhNameCd(null);
		setSrhName01(null);
		setSearchList(new ArrayList<NamesList>());
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
	public List<NamesList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<NamesList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhName01を取得します。
	 * @return srhName01
	 */
	public String getSrhName01() {
		return srhName01;
	}

	/**
	 * srhName01を設定します。
	 * @param srhName01 srhName01
	 */
	public void setSrhName01(final String srhName01) {
		this.srhName01 = srhName01;
	}

	/**
	 * srhNameCdを取得します。
	 * @return srhNameCd
	 */
	public String getSrhNameCd() {
		return srhNameCd;
	}

	/**
	 * srhNameCdを設定します。
	 * @param srhNameCd srhNameCd
	 */
	public void setSrhNameCd(final String srhNameCd) {
		this.srhNameCd = srhNameCd;
	}

	/**
	 * srhNameDivisionを取得します。
	 * @return srhNameDivision
	 */
	public String getSrhNameDivision() {
		return srhNameDivision;
	}

	/**
	 * srhNameDivisionを設定します。
	 * @param srhNameDivision srhNameDivision
	 */
	public void setSrhNameDivision(final String srhNameDivision) {
		this.srhNameDivision = srhNameDivision;
	}

	/**
	 * nameDivisionを取得します。
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return nameDivision;
	}

	/**
	 * nameDivisionを設定します。
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public NamesListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final NamesListConditionForReport condition) {
		this.condition = condition;
	}
}
