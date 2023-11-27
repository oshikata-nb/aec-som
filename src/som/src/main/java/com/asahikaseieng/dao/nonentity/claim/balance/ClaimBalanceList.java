/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.balance;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 請求残高一覧用Daoクラス.
 * @author tosco
 */
public class ClaimBalanceList extends ClaimBalanceListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 前月請求額 */
	private String strClaimAmountForward;

	/** 入金・その他計 */
	private String strOtherCredit;

	/** 今回売上額 */
	private String strSalesAmount;

	/** その他計 */
	private String strOtherSales;

	/** 消費税 */
	private String strTaxAmount;

	/** 請求残高 */
	private String strClaimAmount;

	/** 請求残高 */
	private String strClaimBalanceAmount;

	/**
	 * コンストラクタ.
	 */
	public ClaimBalanceList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrClaimAmountForward(AecNumberUtils.decimalFormat(
		// getClaimAmountForward(), "###,###,###"));
		// setStrOtherCredit(AecNumberUtils.decimalFormat(getOtherCredit(),
		// "###,###,###"));
		// setStrSalesAmount(AecNumberUtils.decimalFormat(getSalesAmount(),
		// "###,###,###"));
		// setStrOtherSales(AecNumberUtils.decimalFormat(getOtherSales(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrClaimAmount(AecNumberUtils.decimalFormat(getClaimAmount(),
		// "###,###,###"));
		// setStrClaimBalanceAmount(AecNumberUtils.decimalFormat(
		// getClaimBalanceAmount(), "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 前回請求額を取得します。
	 * @return strClaimAmountForward
	 */
	public String getStrClaimAmountForward() {
		return strClaimAmountForward;
	}

	/**
	 * 前回請求額を設定します。
	 * @param strClaimAmountForward 前回請求額
	 */
	public void setStrClaimAmountForward(final String strClaimAmountForward) {
		this.strClaimAmountForward = strClaimAmountForward;
	}

	/**
	 * 入金・その他計を取得します。
	 * @return strOtherCredit
	 */
	public String getStrOtherCredit() {
		return strOtherCredit;
	}

	/**
	 * 入金・その他計を設定します。
	 * @param strOtherCredit 入金・その他計
	 */
	public void setStrOtherCredit(final String strOtherCredit) {
		this.strOtherCredit = strOtherCredit;
	}

	/**
	 * 今回売上額を取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 今回売上額を設定します。
	 * @param strSalesAmount 今回売上額
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * その他計を取得します。
	 * @return strOtherSales
	 */
	public String getStrOtherSales() {
		return strOtherSales;
	}

	/**
	 * その他計を設定します。
	 * @param strOtherSales その他計
	 */
	public void setStrOtherSales(final String strOtherSales) {
		this.strOtherSales = strOtherSales;
	}

	/**
	 * 消費税を取得します。
	 * @return strTaxAmount
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 消費税を設定します。
	 * @param strTaxAmount 消費税
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 請求残高を取得します。
	 * @return strClaimAmount
	 */
	public String getStrClaimAmount() {
		return strClaimAmount;
	}

	/**
	 * 請求残高を設定します。
	 * @param strClaimAmount 請求残高
	 */
	public void setStrClaimAmount(final String strClaimAmount) {
		this.strClaimAmount = strClaimAmount;
	}

	/**
	 * mi請求残高を取得します。
	 * @return strClaimBalanceAmount
	 */
	public String getStrClaimBalanceAmount() {
		return strClaimBalanceAmount;
	}

	/**
	 * mi請求残高を設定します。
	 * @param strClaimBalanceAmount 請求残高
	 */
	public void setStrClaimBalanceAmount(final String strClaimBalanceAmount) {
		this.strClaimBalanceAmount = strClaimBalanceAmount;
	}

}
