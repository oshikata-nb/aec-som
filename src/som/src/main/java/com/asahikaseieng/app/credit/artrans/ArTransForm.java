/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.artrans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.credit.artrans.ArTransJournalList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 会計送信 Formクラス.
 * @author t0011036
 */
public class ArTransForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 部署コード */
	private String srhOrganizationCd;

	/* 部署名称 */
	private String srhOrganizationName;

	/* 対象年月 */
	private String srhStrInputDate;

	/* リスト */
	private List<ArTransJournalList> searchList;

	/* ダウンロードフラグ */
	private boolean csvDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public ArTransForm() {
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
		setCsvDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("create".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<ArTransJournalList>());
		}

		if ("insert".equals(getOp()) || "create".equals(getOp())) {
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
		setSrhStrInputDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM"));
		setSearchList(new ArrayList<ArTransJournalList>());
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
	 * srhStrInputDateを取得します。
	 * @return srhStrInputDate
	 */
	public String getSrhStrInputDate() {
		return srhStrInputDate;
	}

	/**
	 * srhStrInputDateを設定します。
	 * @param srhStrInputDate srhStrInputDate
	 */
	public void setSrhStrInputDate(final String srhStrInputDate) {
		this.srhStrInputDate = srhStrInputDate;
	}

	/**
	 * csvDownloadFlgを取得します。
	 * @return csvDownloadFlg
	 */
	public boolean isCsvDownloadFlg() {
		return csvDownloadFlg;
	}

	/**
	 * csvDownloadFlgを設定します。
	 * @param csvDownloadFlg csvDownloadFlg
	 */
	public void setCsvDownloadFlg(final boolean csvDownloadFlg) {
		this.csvDownloadFlg = csvDownloadFlg;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<ArTransJournalList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ArTransJournalList> searchList) {
		this.searchList = searchList;
	}
}
