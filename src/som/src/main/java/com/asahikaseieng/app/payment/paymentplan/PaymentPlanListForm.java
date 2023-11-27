/*
 * Created on 2008/07/09
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.paymentplan;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanList;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 支払予定一覧表 Formクラス
 * @author tosco
 */
public final class PaymentPlanListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

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

	//
	// 検索用.支払予定一覧
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：出力区分 */
	private String srhOutputDivision;

	/** 検索入力：支払先コード */
	private String srhVenderCd;

	/** 検索入力：支払先名称 */
	private String srhVenderName;

	/** 検索入力：支払日付FROM */
	private String srhPaymentDateFrom;

	/** 検索入力：支払日付TO */
	private String srhPaymentDateTo;

	/** 検索入力：銀行コード */
	private String srhBankCd;

	/** 検索入力：銀行名 */
	private String srhBankName;

	/** 検索入力：支払区分 */
	private String srhPaymentDivision;

	/** 検索入力：支払区分リスト */
	private List<ComboBoxItems> srhPaymentList;

	/* 帳票Excel用検索条件 */
	private PaymentPlanListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.支払予定一覧
	//

	/** リスト */
	private List<PaymentPlanList> searchList;

	/**
	 * コンストラクタ.支払予定一覧
	 */
	public PaymentPlanListForm() {
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
		return PaymentPlanPagerCondition.class;
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

		if ("reportPaymentInform".equals(getOp())) {
			/* チェックボックスクリア処理 */
			clearCheck();
		}

		// Excelダウンロードフラグ
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
			setSearchList(new ArrayList<PaymentPlanList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.所属マスタ
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<PaymentPlanList>());

		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：出力区分
		setSrhOutputDivision(null);
		// 検索入力：支払先コード
		setSrhVenderCd(null);
		// 検索入力：支払先名称
		setSrhVenderName(null);
		// 検索入力：支払日付FROM
		setSrhPaymentDateFrom(null);
		// 検索入力：支払日付TO
		setSrhPaymentDateTo(null);
		// 検索入力：銀行コード
		setSrhBankCd(null);
		// 検索入力：銀行名
		setSrhBankName(null);
		// 検索入力：支払区分
		setSrhPaymentDivision(null);
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (PaymentPlanList bean : getSearchList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
	}

	/**
	 * 支払予定一覧表：検索結果リストを取得
	 * @return List<PaymentPlanList> 検索結果リスト
	 * 
	 */
	public List<PaymentPlanList> getSearchList() {
		return searchList;
	}

	/**
	 * 支払予定一覧表：検索結果リストを設定
	 * 
	 * @param searchList 検索結果リスト
	 * 
	 */
	public void setSearchList(final List<PaymentPlanList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.支払予定一覧表

	//

	/**
	 * 検索入力：部署コード取得.
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力：部署コード設定.
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}

	/**
	 * 検索入力：部署名称取得.
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return this.srhOrganizationName;
	}

	/**
	 * 検索入力：部署名称設定.
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(final String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}

	/**
	 * 検索入力：担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 検索入力：担当者コードを設定します。
	 * @param srhTantoCd 検索入力：担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}

	/**
	 * 検索入力：担当者名称を取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 検索入力：担当者名称を設定します。
	 * @param srhTantoNm 検索入力：担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = srhTantoNm;
	}

	/**
	 * 検索入力：支払先FROM取得.
	 * @return String 支払先FROM
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：支払先FROM設定.
	 * @param srhVenderCd 支払先FROM
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：支払先TO取得.
	 * @return String 支払先TO
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 検索入力：支払先TO設定.
	 * @param srhVenderName 支払先TO
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}

	/**
	 * 検索入力：支払日付FROM取得.
	 * @return Date 支払日付FROM
	 */
	public String getSrhPaymentDateFrom() {
		return srhPaymentDateFrom;
	}

	/**
	 * 検索入力：支払日付FROM設定.
	 * @param srhPaymentDateFrom 支払日付FROM
	 */
	public void setSrhPaymentDateFrom(final String srhPaymentDateFrom) {
		this.srhPaymentDateFrom = srhPaymentDateFrom;
	}

	/**
	 * 検索入力：支払日付TO取得.
	 * @return Date 支払日付TO
	 */
	public String getSrhPaymentDateTo() {
		return srhPaymentDateTo;
	}

	/**
	 * 検索入力：支払日付TO設定.
	 * @param srhPaymentDateTo 支払日付TO
	 */
	public void setSrhPaymentDateTo(final String srhPaymentDateTo) {
		this.srhPaymentDateTo = srhPaymentDateTo;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return String 出力区分
	 * 
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 * 
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * 検索入力：銀行コードを取得
	 * 
	 * @return srhBankCd
	 */
	public String getSrhBankCd() {
		return srhBankCd;
	}

	/**
	 * 検索入力：銀行コードを設定
	 * 
	 * @param srhBankCd 銀行コード
	 * 
	 */
	public void setSrhBankCd(final String srhBankCd) {
		this.srhBankCd = srhBankCd;
	}

	/**
	 * 検索入力：銀行名を取得
	 * 
	 * @return srhBankName
	 */
	public String getSrhBankName() {
		return srhBankName;
	}

	/**
	 * 検索入力：銀行名を設定
	 * 
	 * @param srhBankName 銀行名
	 */
	public void setSrhBankName(final String srhBankName) {
		this.srhBankName = srhBankName;
	}

	/**
	 * 検索入力：支払区分を取得
	 * 
	 * @return srhPaymentDivision
	 */
	public String getSrhPaymentDivision() {
		return srhPaymentDivision;
	}

	/**
	 * 検索入力：支払区分を設定
	 * 
	 * @param srhPaymentDivision 支払区分
	 * 
	 */
	public void setSrhPaymentDivision(final String srhPaymentDivision) {
		this.srhPaymentDivision = srhPaymentDivision;
	}

	/**
	 * dirtyFlgを取得
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * srhPaymentListを取得します。
	 * @return srhPaymentList
	 */
	public List<ComboBoxItems> getSrhPaymentList() {
		return srhPaymentList;
	}

	/**
	 * srhPaymentListを設定します。
	 * @param srhPaymentList srhPaymentList
	 */
	public void setSrhPaymentList(final List<ComboBoxItems> srhPaymentList) {
		this.srhPaymentList = srhPaymentList;
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
	public PaymentPlanListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final PaymentPlanListConditionForReport condition) {
		this.condition = condition;
	}
}
