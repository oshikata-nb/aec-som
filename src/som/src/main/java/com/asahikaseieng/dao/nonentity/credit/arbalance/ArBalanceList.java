/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売掛残高一覧用Daoクラス.
 * @author tosco
 */
public class ArBalanceList extends ArBalanceListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 前月残 */
	private String strBalanceForward;

	/** 入金・その他計 */
	private String strOtherDeposit;

	/** 当月売上高 */
	private String strSalesAmount;

	/** その他計 */
	private String strOtherSales;

	/** 消費税額 */
	private String strTaxAmount;

	/** 売掛金(内訳) */
	private String strCreditSalesBreakdown;

	/** 請求残高 */
	private String strClaimBalance;

	/** 未請求残高 */
	private String strClaimForward;

	/** 未収金(内訳) */
	private String strAccruedDebitBreakdown;

	/** 当月残 */
	private String strCreditAmount;

	/**
	 * コンストラクタ.
	 */
	public ArBalanceList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrBalanceForward(AecNumberUtils.decimalFormat(getBalanceForward(),
		// "###,###,###"));
		// setStrOtherDeposit(AecNumberUtils.decimalFormat(getOtherDeposit(),
		// "###,###,###"));
		// setStrSalesAmount(AecNumberUtils.decimalFormat(getSalesAmount(),
		// "###,###,###"));
		// setStrOtherSales(AecNumberUtils.decimalFormat(getOtherSales(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrCreditSalesBreakdown(AecNumberUtils.decimalFormat(
		// getCreditSalesBreakdown(), "###,###,###"));
		// setStrAccruedDebitBreakdown(AecNumberUtils.decimalFormat(
		// getAccruedDebitBreakdown(), "###,###,###"));
		// setStrCreditAmount(AecNumberUtils.decimalFormat(getCreditAmount(),
		// "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 前月残を取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * 前月残を設定します。
	 * @param strBalanceForward 前月残
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * 当月残を取得します。
	 * @return strCreditAmount
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 当月残を設定します。
	 * @param strCreditAmount 当月残
	 */
	public void setStrCreditAmount(final String strCreditAmount) {
		this.strCreditAmount = strCreditAmount;
	}

	/**
	 * 入金・その他計を取得します。
	 * @return strOtherDeposit
	 */
	public String getStrOtherDeposit() {
		return strOtherDeposit;
	}

	/**
	 * 入金・その他計を設定します。
	 * @param strOtherDeposit 入金・その他計
	 */
	public void setStrOtherDeposit(final String strOtherDeposit) {
		this.strOtherDeposit = strOtherDeposit;
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
	 * 当月売上高を取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 当月売上高を設定します。
	 * @param strSalesAmount 当月売上高
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * 消費税額を取得します。
	 * @return strTaxAmount
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 消費税額を設定します。
	 * @param strTaxAmount 消費税額
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 未収金(内訳)を取得します。
	 * @return strAccruedDebitBreakdown
	 */
	public String getStrAccruedDebitBreakdown() {
		return strAccruedDebitBreakdown;
	}

	/**
	 * 未収金(内訳)を設定します。
	 * @param strAccruedDebitBreakdown 未収金(内訳)
	 */
	public void setStrAccruedDebitBreakdown(
			final String strAccruedDebitBreakdown) {
		this.strAccruedDebitBreakdown = strAccruedDebitBreakdown;
	}

	/**
	 * 売掛金(内訳)を取得します。
	 * @return strCreditSalesBreakdown
	 */
	public String getStrCreditSalesBreakdown() {
		return strCreditSalesBreakdown;
	}

	/**
	 * 売掛金(内訳)を設定します。
	 * @param strCreditSalesBreakdown 売掛金(内訳)
	 */
	public void setStrCreditSalesBreakdown(final String strCreditSalesBreakdown) {
		this.strCreditSalesBreakdown = strCreditSalesBreakdown;
	}

	/**
	 * strClaimBalanceを取得します。
	 * @return strClaimBalance
	 */
	public String getStrClaimBalance() {
		return strClaimBalance;
	}

	/**
	 * strClaimBalanceを設定します。
	 * @param strClaimBalance strClaimBalance
	 */
	public void setStrClaimBalance(final String strClaimBalance) {
		this.strClaimBalance = strClaimBalance;
	}

	/**
	 * strClaimForwardを取得します。
	 * @return strClaimForward
	 */
	public String getStrClaimForward() {
		return strClaimForward;
	}

	/**
	 * strClaimForwardを設定します。
	 * @param strClaimForward strClaimForward
	 */
	public void setStrClaimForward(final String strClaimForward) {
		this.strClaimForward = strClaimForward;
	}
}
