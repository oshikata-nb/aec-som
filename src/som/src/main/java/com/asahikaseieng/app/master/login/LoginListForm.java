/*
 * Created on 2007/07/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginList;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 担当者マスタ一覧 Formクラス
 * @author t0011036
 */
public final class LoginListForm extends AbstractSearchForm {

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

	/* 組織コード */
	private String srhOrganizationCd;

	/* 組織名称 */
	private String srhOrganizationName;

	/* 担当者コード */
	private String srhTantoCd;

	/* 担当者名称 */
	private String srhTantoNm;

	/* 役職コード */
	private String srhPostId;

	/* 役職名称 */
	private String srhPostName;

	/* 担当者リスト */
	private List<LoginList> searchList;

	/* 検索区分ラジオ */
	private String tantoSelCondition;

	/* ログイン担当者コード */
	private String loginTantoCd;

	/* ログイン管理者区分 */
	private String loginAdminFlg;

	/* 帳票Excel用検索条件 */
	private LoginListConditionForReport condition;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.担当者マスタ
	 */
	public LoginListForm() {
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
		return LoginListPagerCondition.class;
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
			setSearchList(new ArrayList<LoginList>());
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
		setSrhOrganizationCd(null);
		setSrhOrganizationName(null);
		setSrhTantoCd(null);
		setSrhTantoNm(null);
		setSrhPostId(null);
		setSrhPostName(null);
		setTantoSelCondition("0");
		setLoginTantoCd(null);
		setLoginAdminFlg(null);
		setSearchList(new ArrayList<LoginList>());
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
	public List<LoginList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<LoginList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * srhOrganizationNameを取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}

	/**
	 * srhOrganizationNameを設定します。
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * srhPostIdを取得します。
	 * @return srhPostId
	 */
	public String getSrhPostId() {
		return srhPostId;
	}

	/**
	 * srhPostIdを設定します。
	 * @param srhPostId srhPostId
	 */
	public void setSrhPostId(final String srhPostId) {
		this.srhPostId = srhPostId;
	}

	/**
	 * srhPostNameを取得します。
	 * @return srhPostName
	 */
	public String getSrhPostName() {
		return srhPostName;
	}

	/**
	 * srhPostNameを設定します。
	 * @param srhPostName srhPostName
	 */
	public void setSrhPostName(final String srhPostName) {
		this.srhPostName = srhPostName;
	}

	/**
	 * srhTantoCdを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * srhTantoCdを設定します。
	 * @param srhTantoCd srhTantoCd
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * srhTantoNmを取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * srhTantoNmを設定します。
	 * @param srhTantoNm srhTantoNm
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * tantoSelConditionを取得します。
	 * @return tantoSelCondition
	 */
	public String getTantoSelCondition() {
		return tantoSelCondition;
	}

	/**
	 * tantoSelConditionを設定します。
	 * @param tantoSelCondition tantoSelCondition
	 */
	public void setTantoSelCondition(final String tantoSelCondition) {
		this.tantoSelCondition = tantoSelCondition;
	}

	/**
	 * loginAdminFlgを取得します。
	 * @return loginAdminFlg
	 */
	public String getLoginAdminFlg() {
		return loginAdminFlg;
	}

	/**
	 * loginAdminFlgを設定します。
	 * @param loginAdminFlg loginAdminFlg
	 */
	public void setLoginAdminFlg(final String loginAdminFlg) {
		this.loginAdminFlg = loginAdminFlg;
	}

	/**
	 * loginTantoCdを取得します。
	 * @return loginTantoCd
	 */
	public String getLoginTantoCd() {
		return loginTantoCd;
	}

	/**
	 * loginTantoCdを設定します。
	 * @param loginTantoCd loginTantoCd
	 */
	public void setLoginTantoCd(final String loginTantoCd) {
		this.loginTantoCd = loginTantoCd;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public LoginListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final LoginListConditionForReport condition) {
		this.condition = condition;
	}
}
