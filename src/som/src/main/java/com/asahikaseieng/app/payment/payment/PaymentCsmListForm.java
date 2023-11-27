/*
 * Created on 2008/10/03
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 支払入力 Formクラス(カスタマイズ)
 * @author tosco
 */
public final class PaymentCsmListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.paymentcsm.list"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.paymentcsm.list"));
	}

	//
	// 検索用.支払入力

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：支払日付FROM */
	private String srhPaymentDateFrom;

	/** 検索入力：支払日付TO */
	private String srhPaymentDateTo;

	/** 検索入力：支払先コード */
	private String srhCustomerCd;

	/** 検索入力：支払先名称 */
	private String srhCustomerName;

	/** 検索入力：支払番号FROM */
	private String srhSlipNoFrom;

	/** 検索入力：支払番号TO */
	private String srhSlipNoTo;

	/** 検索入力：支払分類 */
	private String srhPaymentDivision;

	/** 出力区分 */
	private String srhOutputDivision;

	/** 伝票発行チェック */
	private boolean srhIssuedCheck;

	/** 支払分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	/* 帳票Excel用検索条件 */
	private PaymentListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.支払入力

	/** リスト */
	private List<AltPayment> searchList;

	/**
	 * コンストラクタ.支払入力
	 */
	public PaymentCsmListForm() {
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
		return AltPaymentPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		// 伝票発行チェックボックスクリア
		srhIssuedCheck = false;

		if ("init".equals(getOp())) {
			clear();
		}

		if ("report".equals(getOp()) || "reportTegata".equals(getOp())
				|| "reportPaymentInform".equals(getOp())) {
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
			setSearchList(new ArrayList<AltPayment>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.支払入力
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(null);

		/* 検索入力：部署コード */
		setSrhOrganizationCd(null);
		/* 検索入力：部署名称 */
		setSrhOrganizationName(null);
		/* 検索入力：担当者コード */
		setSrhTantoCd(null);
		/* 検索入力：担当者名称 */
		setSrhTantoNm(null);
		/* 検索入力：支払日付FROM */
		setSrhPaymentDateFrom(null);
		/* 検索入力：支払日付TO */
		setSrhPaymentDateTo(null);
		/* 検索入力：支払先コード */
		setSrhCustomerCd(null);
		/* 検索入力：支払先名称 */
		setSrhCustomerName(null);
		/* 検索入力：支払番号FROM */
		setSrhSlipNoFrom(null);
		/* 検索入力：支払番号TO */
		setSrhSlipNoTo(null);
		/* 検索入力：支払区分 */
		setSrhPaymentDivision(null);
		/* 検索入力：伝票発行チェック */
		setSrhIssuedCheck(false);
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (AltPayment bean : getSearchList()) {
				bean.setChecked(Boolean.FALSE);
			}
		}
	}

	/**
	 * 支払入力：検索結果リストを取得します。
	 * @return List<Payment> 検索結果リスト
	 */
	public List<AltPayment> getSearchList() {
		return searchList;
	}

	/**
	 * 支払入力：検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<AltPayment> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.支払入力

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
	 * 検索入力：支払日付FROM取得.
	 * @return String 支払日付FROM
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
	 * @return String 支払日付TO
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
	 * 検索入力：支払先コード取得.
	 * @return String 支払先コード
	 * 
	 */
	public String getSrhCustomerCd() {
		return srhCustomerCd;
	}

	/**
	 * 検索入力：支払先コード設定.
	 * @param srhCustomerCd 支払先コード
	 * 
	 */
	public void setSrhCustomerCd(final String srhCustomerCd) {
		this.srhCustomerCd = srhCustomerCd;
	}

	/**
	 * 検索入力：支払先名称取得.
	 * @return String 支払先名称
	 */
	public String getSrhCustomerName() {
		return srhCustomerName;
	}

	/**
	 * 検索入力：支払先名称設定.
	 * @param srhCustomerName 支払先名称
	 */
	public void setSrhCustomerName(final String srhCustomerName) {
		this.srhCustomerName = srhCustomerName;
	}

	/**
	 * 検索入力：支払番号FROM取得.
	 * @return String 支払番号FROM
	 */
	public String getSrhSlipNoFrom() {
		return srhSlipNoFrom;
	}

	/**
	 * 検索入力：支払番号FROM設定.
	 * @param srhSlipNoFrom 支払番号FROM
	 */
	public void setSrhSlipNoFrom(final String srhSlipNoFrom) {
		this.srhSlipNoFrom = srhSlipNoFrom;
	}

	/**
	 * 検索入力：支払番号TO取得.
	 * @return String 支払番号TO
	 */
	public String getSrhSlipNoTo() {
		return srhSlipNoTo;
	}

	/**
	 * 検索入力：支払番号TO設定.
	 * @param srhSlipNoTo 支払番号TO
	 */
	public void setSrhSlipNoTo(final String srhSlipNoTo) {
		this.srhSlipNoTo = srhSlipNoTo;
	}

	/**
	 * 検索入力：支払分類取得.
	 * @return BigDecimal 支払分類
	 * 
	 */
	public String getSrhPaymentDivision() {
		return srhPaymentDivision;
	}

	/**
	 * 検索入力：支払分類設定.
	 * @param srhPaymentDivision 支払分類
	 * 
	 */
	public void setSrhPaymentDivision(final String srhPaymentDivision) {
		this.srhPaymentDivision = srhPaymentDivision;
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
	 * 検索入力：伝票発行チェック取得.
	 * @return String 伝票発行チェック
	 */
	public boolean getSrhIssuedCheck() {
		return srhIssuedCheck;
	}

	/**
	 * 検索入力：伝票発行チェック設定.
	 * @param srhIssuedCheck 伝票発行チェック
	 */
	public void setSrhIssuedCheck(final boolean srhIssuedCheck) {
		this.srhIssuedCheck = srhIssuedCheck;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 支払分類コンボボックス内容を取得します。
	 * @return 支払分類コンボボックス内容
	 */
	public List<ComboBoxItems> getCategoryList() {
		return categoryList;
	}

	/**
	 * 支払分類コンボボックス内容を設定します。
	 * @param categoryList 支払分類コンボボックス内容
	 */
	public void setCategoryList(final List<ComboBoxItems> categoryList) {
		this.categoryList = categoryList;
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
	public PaymentListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final PaymentListConditionForReport condition) {
		this.condition = condition;
	}
}
