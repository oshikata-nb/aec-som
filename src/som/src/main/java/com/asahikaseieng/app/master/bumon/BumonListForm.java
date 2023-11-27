/*
 * Created on 2007/07/25
 *
 * $copyright$
 * tanaka:bumonマスタ
 */
package com.asahikaseieng.app.master.bumon;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonList;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 会計部門マスタ一覧 Formクラス
 * @author TanakaSatoru
 */
public final class BumonListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// インスタンスフィールド
	//

	/** 検索入力：会計部門コード */
	private String srhSectionCd;

	/** 検索入力：会計部門名称 */
	private String srhSectionName;

	/** リスト */
	private List<BumonList> searchList;

	/** 帳票Excel用検索条件 */
	private BumonListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public BumonListForm() {
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
		return BumonListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
			setSearchList(new ArrayList<BumonList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア.
	 */
	public void clear() {
		/** 検索リストのクリア */
		setSearchList(new ArrayList<BumonList>());
		/** 検索入力：会計部門コード */
		setSrhSectionCd(null);
		/** 検索入力：会計部門名称 */
		setSrhSectionName(null);
		/** 検索条件 */
		setCondition(null);
	}

	//
	// インスタンスメソッド.
	//

	/**
	 * 会計部門マスタ：searchListを取得します。
	 * @return searchList
	 */
	public List<BumonList> getSearchList() {
		return searchList;
	}

	/**
	 * 会計部門マスタ： searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BumonList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 検索入力：会計部門コード取得.
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return this.srhSectionCd;
	}

	/**
	 * 検索入力：会計部門コード設定.
	 * @param srhSectionCd sectionCd
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = srhSectionCd;
	}

	/**
	 * 検索入力：会計部門名称取得.
	 * @return srhSectionName
	 */
	public String getSrhSectionName() {
		return this.srhSectionName;
	}

	/**
	 * 検索入力：会計部門名称設定.
	 * @param srhSectionName srhSectionName
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = srhSectionName;
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
	public BumonListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final BumonListConditionForReport condition) {
		this.condition = condition;
	}
}
