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
 * 棚卸準備キャンセル処理 Formクラス
 * @author tanaka
 */
public class InquiryPreparationCancelForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryPreparationCancelForm() {
		super();
	}

	/* 棚卸準備処理日 */
	private Timestamp srhCountDate;

	private String strSrhCountDate;

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
		if ("delete".equals(getOp())) {
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
		setStrSrhCountDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
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
}
