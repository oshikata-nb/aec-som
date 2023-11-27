/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.banklist.BankList;
import com.asahikaseieng.dao.nonentity.master.banklist.BankListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 銀行一覧 Formクラス.
 * @author t0011036
 */
public final class BankListForm extends AbstractSearchForm {

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

	/* 銀行コード */
	private String srhBankCd;

	/* 銀行名称 */
	private String srhBankName;

	/* 支店コード */
	private String srhBranchCd;

	/* 支店名称 */
	private String srhBranchName;

	/* 銀行マスタコード */
	private String srhBankMasterCd;

	/* 銀行マスタ名称 */
	private String srhBankMasterName;

	/* 銀行リスト */
	private List<BankList> searchList;

	/* 帳票Excel用検索条件 */
	private BankListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public BankListForm() {
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
		return BankListPagerCondition.class;
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
			setSearchList(new ArrayList<BankList>());
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
		setSrhBankCd(null);
		setSrhBankName(null);
		setSrhBranchCd(null);
		setSrhBranchName(null);
		setSrhBankMasterCd(null);
		setSrhBankMasterName(null);
		setSearchList(new ArrayList<BankList>());
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
	public List<BankList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<BankList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhBankCdを取得します。
	 * @return srhBankCd
	 */
	public String getSrhBankCd() {
		return srhBankCd;
	}

	/**
	 * srhBankCdを設定します。
	 * @param srhBankCd srhBankCd
	 */
	public void setSrhBankCd(final String srhBankCd) {
		this.srhBankCd = srhBankCd;
	}

	/**
	 * srhBankNameを取得します。
	 * @return srhBankName
	 */
	public String getSrhBankName() {
		return srhBankName;
	}

	/**
	 * srhBankNameを設定します。
	 * @param srhBankName srhBankName
	 */
	public void setSrhBankName(final String srhBankName) {
		this.srhBankName = srhBankName;
	}

	/**
	 * srhBankMasterCdを取得します。
	 * @return srhBankMasterCd
	 */
	public String getSrhBankMasterCd() {
		return srhBankMasterCd;
	}

	/**
	 * srhBankMasterCdを設定します。
	 * @param srhBankMasterCd srhBankMasterCd
	 */
	public void setSrhBankMasterCd(final String srhBankMasterCd) {
		this.srhBankMasterCd = srhBankMasterCd;
	}

	/**
	 * srhBankMasterNameを取得します。
	 * @return srhBankMasterName
	 */
	public String getSrhBankMasterName() {
		return srhBankMasterName;
	}

	/**
	 * srhBankMasterNameを設定します。
	 * @param srhBankMasterName srhBankMasterName
	 */
	public void setSrhBankMasterName(final String srhBankMasterName) {
		this.srhBankMasterName = srhBankMasterName;
	}

	/**
	 * srhBranchCdを取得します。
	 * @return srhBranchCd
	 */
	public String getSrhBranchCd() {
		return srhBranchCd;
	}

	/**
	 * srhBranchCdを設定します。
	 * @param srhBranchCd srhBranchCd
	 */
	public void setSrhBranchCd(final String srhBranchCd) {
		this.srhBranchCd = srhBranchCd;
	}

	/**
	 * srhBranchNameを取得します。
	 * @return srhBranchName
	 */
	public String getSrhBranchName() {
		return srhBranchName;
	}

	/**
	 * srhBranchNameを設定します。
	 * @param srhBranchName srhBranchName
	 */
	public void setSrhBranchName(final String srhBranchName) {
		this.srhBranchName = srhBranchName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public BankListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final BankListConditionForReport condition) {
		this.condition = condition;
	}
}
