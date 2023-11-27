/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsList;
import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 勘定科目一覧 Formクラス.
 * @author t0011036
 */
public final class AccountsListForm extends AbstractSearchForm {

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

	/* 勘定科目コード */
	private String srhAccountsCd;

	/* 勘定科目名称 */
	private String srhAccountsName;

	/* 勘定科目リスト */
	private List<AccountsList> searchList;

	/* 帳票Excel用検索条件 */
	private AccountsListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public AccountsListForm() {
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
		return AccountsListPagerCondition.class;
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
			setSearchList(new ArrayList<AccountsList>());
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
		setSrhAccountsCd(null);
		setSrhAccountsName(null);
		setSearchList(new ArrayList<AccountsList>());
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
	public List<AccountsList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<AccountsList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhAccountsCdを取得します。
	 * @return srhAccountsCd
	 */
	public String getSrhAccountsCd() {
		return srhAccountsCd;
	}

	/**
	 * srhAccountsCdを設定します。
	 * @param srhAccountsCd srhAccountsCd
	 */
	public void setSrhAccountsCd(final String srhAccountsCd) {
		this.srhAccountsCd = srhAccountsCd;
	}

	/**
	 * srhAccountsNameを取得します。
	 * @return srhAccountsName
	 */
	public String getSrhAccountsName() {
		return srhAccountsName;
	}

	/**
	 * srhAccountsNameを設定します。
	 * @param srhAccountsName srhAccountsName
	 */
	public void setSrhAccountsName(final String srhAccountsName) {
		this.srhAccountsName = srhAccountsName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public AccountsListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final AccountsListConditionForReport condition) {
		this.condition = condition;
	}
}
