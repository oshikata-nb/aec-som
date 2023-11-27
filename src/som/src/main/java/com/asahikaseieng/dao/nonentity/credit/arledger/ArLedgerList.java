/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売掛元帳用クラス.
 * @author tosco
 */
public class ArLedgerList extends ArLedgerListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 入金・その他計 */
	private String strOtherDeposit;

	/** 売上金額 */
	private String strSalesAmount;

	/** その他計 */
	private String strOtherSales;

	/** 消費税額 */
	private String strTaxAmount;

	/** 売掛金(内訳) */
	private String strCreditSalesBreakdown;

	/** 未収金(内訳) */
	private String strAccruedDebitBreakdown;

	/** 売掛残高 */
	private String strCreditAmount;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerList() {
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
		// setStrCreditSalesBreakdown(AecNumberUtils.decimalFormat(getCreditSalesBreakdown(),
		// "###,###,###"));
		// setStrAccruedDebitBreakdown(AecNumberUtils.decimalFormat(getAccruedDebitBreakdown(),
		// "###,###,###"));
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
	 * 前月繰越を取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * 前月繰越を設定します。
	 * @param strBalanceForward 前月繰越
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * 売掛残高を取得します。
	 * @return strCreditAmount
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 売掛残高を設定します。
	 * @param strCreditAmount 売掛残高
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
	 * 売上金額を取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 売上金額を設定します。
	 * @param strSalesAmount 売上金額
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

}
