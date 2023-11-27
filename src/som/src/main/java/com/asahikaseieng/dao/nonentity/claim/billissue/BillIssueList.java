/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissue;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 請求書発行 一覧画面用Daoクラス.
 * @author tosco
 */
public class BillIssueList extends BillIssueListBase implements
		PropertyTransferCallbacker {

	/** 請求締め日 */
	private String strCreditDate;

	/** 請求番号 */
	private String claimNo;

	/** 前回請求額 */
	private String strClaimAmountForward;

	/** 入金・その他計 */
	private String strOtherCreditAmount;

	/** 今回売上額 */
	private String strSalesAmount;

	/** その他計 */
	private String strOtherSales;

	/** 消費税 */
	private String strTaxAmount;

	/** 請求合計 */
	private String strClaimAmount;


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BillIssueList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrClaimAmountForward(AecNumberUtils.decimalFormat(getClaimAmountForward(),
		// "###,###,###"));
		// setStrOtherCreditAmount(AecNumberUtils.decimalFormat(getOtherCreditAmount(),
		// "###,###,###"));
		// setStrSalesAmount(AecNumberUtils.decimalFormat(getSalesAmount(),
		// "###,###,###"));
		// setStrOtherSales(AecNumberUtils.decimalFormat(getOtherSales(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrClaimAmount(AecNumberUtils.decimalFormat(getClaimAmount(),
		//			"###,###,###"));
		/* 請求書発行フラグをチェック */
		setBillIssueFlg(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * claimNoを取得します。
	 * @return claimNo
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * claimNoを設定します。
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * strClaimAmountを取得します。
	 * @return strClaimAmount
	 */
	public String getStrClaimAmount() {
		return strClaimAmount;
	}

	/**
	 * strClaimAmountを設定します。
	 * @param strClaimAmount strClaimAmount
	 */
	public void setStrClaimAmount(final String strClaimAmount) {
		this.strClaimAmount = strClaimAmount;
	}

	/**
	 * strClaimAmountForwardを取得します。
	 * @return strClaimAmountForward
	 */
	public String getStrClaimAmountForward() {
		return strClaimAmountForward;
	}

	/**
	 * strClaimAmountForwardを設定します。
	 * @param strClaimAmountForward strClaimAmountForward
	 */
	public void setStrClaimAmountForward(final String strClaimAmountForward) {
		this.strClaimAmountForward = strClaimAmountForward;
	}

	/**
	 * strCreditDateを取得します。
	 * @return strCreditDate
	 */
	public String getStrCreditDate() {
		return strCreditDate;
	}

	/**
	 * strCreditDateを設定します。
	 * @param strCreditDate strCreditDate
	 */
	public void setStrCreditDate(final String strCreditDate) {
		this.strCreditDate = strCreditDate;
	}

	/**
	 * strOtherCreditAmountを取得します。
	 * @return strOtherCreditAmount
	 */
	public String getStrOtherCreditAmount() {
		return strOtherCreditAmount;
	}

	/**
	 * strOtherCreditAmountを設定します。
	 * @param strOtherCreditAmount strOtherCreditAmount
	 */
	public void setStrOtherCreditAmount(final String strOtherCreditAmount) {
		this.strOtherCreditAmount = strOtherCreditAmount;
	}

	/**
	 * strOtherSalesAmountを取得します。
	 * @return strOtherSales
	 */
	public String getStrOtherSales() {
		return strOtherSales;
	}

	/**
	 * strOtherSalesAmountを設定します。
	 * @param strOtherSales strOtherSales
	 */
	public void setStrOtherSales(final String strOtherSales) {
		this.strOtherSales = strOtherSales;
	}

	/**
	 * strSalesAmountを取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * strSalesAmountを設定します。
	 * @param strSalesAmount strSalesAmount
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * strTaxAmountを取得します。
	 * @return strTaxAmount
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * strTaxAmountを設定します。
	 * @param strTaxAmount strTaxAmount
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

}

