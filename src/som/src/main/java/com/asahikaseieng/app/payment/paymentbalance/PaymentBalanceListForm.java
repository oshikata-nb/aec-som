/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.paymentbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceList;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 支払残高一覧 Formクラス
 * @author tosco
 */
public final class PaymentBalanceListForm extends AbstractSearchForm {

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
	// 検索用.支払残高一覧
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：部署名称 */
	private String srhOrganizationName;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：支払先コード */
	private String srhVenderCd;

	/** 検索入力：支払先名称 */
	private String srhVenderName;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：出力区分 */
	private boolean srhOutputDivision;

	/* 帳票Excel用検索条件 */
	private PaymentBalanceListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// 一覧用.支払残高一覧
	//

	/** リスト */
	private List<PaymentBalanceList> searchList;

	/**
	 * コンストラクタ.支払残高一覧
	 */
	public PaymentBalanceListForm() {
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
		return PaymentBalancePagerCondition.class;
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
		if ("search".equals(getOp())) {
			// 検索入力：出力区分
			setSrhOutputDivision(false);
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
			setSearchList(new ArrayList<PaymentBalanceList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.支払残高一覧
	 */
	public void clear() {

		// 検索リストのクリア */
		setSearchList(new ArrayList<PaymentBalanceList>());

		// 検索入力：部署コード
		setSrhOrganizationCd(null);
		// 検索入力：部署名称
		setSrhOrganizationName(null);
		// 検索入力：担当者コード
		setSrhTantoCd(null);
		// 検索入力：担当者名称
		setSrhTantoNm(null);
		// 検索入力：支払先コード
		setSrhVenderCd(null);
		// 検索入力：支払先名称
		setSrhVenderName(null);
		// 検索入力：対象年月
		setSrhTargetMonth(null);
		// 検索入力：出力区分
		setSrhOutputDivision(false);
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * 支払残高一覧表：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<PaymentBalanceList> getSearchList() {
		return searchList;
	}

	/**
	 * 支払残高一覧表：searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<PaymentBalanceList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.支払残高一覧表
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
	 * @param srhOrganizationCd organizationCd
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
	 * 対象年月を取得します。
	 * 
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 対象年月を設定します。
	 * 
	 * @param srhTargetMonth 対象年月
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
	}

	/**
	 * 検索入力：出力区分取得.
	 * @return boolean 出力区分
	 * 
	 */
	public boolean isSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 検索入力：出力区分設定.
	 * @param srhOutputDivision 出力区分
	 * 
	 */
	public void setSrhOutputDivision(final boolean srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public PaymentBalanceListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(
			final PaymentBalanceListConditionForReport condition) {
		this.condition = condition;
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
}
