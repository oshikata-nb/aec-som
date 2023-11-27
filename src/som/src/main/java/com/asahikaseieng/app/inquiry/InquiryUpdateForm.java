/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 棚卸更新処理 Formクラス
 * @author tanaka
 */
public class InquiryUpdateForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryUpdateForm() {
		super();
	}

	/* 棚卸準備処理日 */
	private Timestamp srhCountDate;

	private String strSrhCountDate;

	/* 棚卸更新日 */
	private Timestamp srhUpdateDate;

	private String strSrhUpdateDate;

	/* 棚卸区分 */
	private String srhCountDivision;

	/* 棚卸区分名称 */
	private String[] srhCountDivisionValues;

	private String[] srhCountDivisionLabels;

	/* リスト */
	private List<InquiryPreparationList> searchList;

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

		if ("dellist".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setSrhCountDate(AecDateUtils.getCurrentTimestamp());
		setStrSrhCountDate(AecDateUtils.dateFormat(getSrhCountDate(),
			"yyyy/MM/dd"));
		setSrhUpdateDate(AecDateUtils.getCurrentTimestamp());
		setStrSrhUpdateDate(AecDateUtils.dateFormat(getSrhUpdateDate(),
			"yyyy/MM/dd"));
		setSrhCountDivision(null);
		setSrhCountDivisionValues(null);
		setSrhCountDivisionLabels(null);
		setSearchList(new ArrayList<InquiryPreparationList>());
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (InquiryPreparationList bean : getSearchList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<InquiryPreparationList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<InquiryPreparationList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhCountDateを取得します。
	 * @return srhCountDate
	 */
	public Timestamp getSrhCountDate() {
		return srhCountDate;
	}

	/**
	 * srhCountDateを設定します。
	 * @param srhCountDate srhCountDate
	 */
	public void setSrhCountDate(final Timestamp srhCountDate) {
		this.srhCountDate = srhCountDate;
	}

	/**
	 * srhCountDivisionを取得します。
	 * @return srhCountDivision
	 */
	public String getSrhCountDivision() {
		return srhCountDivision;
	}

	/**
	 * srhCountDivisionを設定します。
	 * @param srhCountDivision srhCountDivision
	 */
	public void setSrhCountDivision(final String srhCountDivision) {
		this.srhCountDivision = srhCountDivision;
	}

	/**
	 * srhCountDivisionLabelsを取得します。
	 * @return srhCountDivisionLabels
	 */
	public String[] getSrhCountDivisionLabels() {
		return srhCountDivisionLabels;
	}

	/**
	 * srhCountDivisionLabelsを設定します。
	 * @param srhCountDivisionLabels srhCountDivisionLabels
	 */
	public void setSrhCountDivisionLabels(final String[] srhCountDivisionLabels) {
		this.srhCountDivisionLabels = srhCountDivisionLabels;
	}

	/**
	 * srhCountDivisionValuesを取得します。
	 * @return srhCountDivisionValues
	 */
	public String[] getSrhCountDivisionValues() {
		return srhCountDivisionValues;
	}

	/**
	 * srhCountDivisionValuesを設定します。
	 * @param srhCountDivisionValues srhCountDivisionValues
	 */
	public void setSrhCountDivisionValues(final String[] srhCountDivisionValues) {
		this.srhCountDivisionValues = srhCountDivisionValues;
	}

	/**
	 * strSrhCountDateを取得します。
	 * @return strSrhCountDate
	 */
	public String getStrSrhCountDate() {
		return strSrhCountDate;
	}

	/**
	 * strSrhCountDateを設定します。
	 * @param strSrhCountDate strSrhCountDate
	 */
	public void setStrSrhCountDate(final String strSrhCountDate) {
		this.strSrhCountDate = strSrhCountDate;
	}

	/**
	 * srhUpdateDateを取得します。
	 * @return srhUpdateDate
	 */
	public Timestamp getSrhUpdateDate() {
		return srhUpdateDate;
	}

	/**
	 * srhUpdateDateを設定します。
	 * @param srhUpdateDate srhUpdateDate
	 */
	public void setSrhUpdateDate(final Timestamp srhUpdateDate) {
		this.srhUpdateDate = srhUpdateDate;
	}

	/**
	 * strSrhUpdateDateを取得します。
	 * @return strSrhUpdateDate
	 */
	public String getStrSrhUpdateDate() {
		return strSrhUpdateDate;
	}

	/**
	 * strSrhUpdateDateを設定します。
	 * @param strSrhUpdateDate strSrhUpdateDate
	 */
	public void setStrSrhUpdateDate(final String strSrhUpdateDate) {
		this.strSrhUpdateDate = strSrhUpdateDate;
	}
}
