/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売掛元帳詳細用クラス.
 * @author tosco
 */
public class ArLedgerDetail extends ArLedgerDetailBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 入金金額 */
	private String strDepositAmount;

	/** その他入金金額 */
	private String strOtherDepositAmount;

	/** 売上高 */
	private String strSalesAmount;

	/** 返品金額 */
	private String strReturnedAmount;

	/** 値引金額 */
	private String strDiscountAmount;

	/** その他売上金額 */
	private String strOtherSalesAmount;

	/** 相殺金額 */
	private String strOffsetAmount;

	/** 消費税額 */
	private String strTaxAmount;

	/** 売掛金(内訳) */
	private String strCreditSalesBreakdown;

	/** 未収金(内訳) */
	private String strAccruedDebitBreakdown;

	/** 売掛残高 */
	private String strCreditAmount;


	/** 行番号 */
	private String strRowNo;

	/** 売上高 */
	private String strSales;

	/** 入金額 */
	private String strCredit;

	/** 残高 */
	private String strBalance;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// ヘッダー部分
		// setStrBalanceForward(AecNumberUtils.decimalFormat(getBalanceForward(),
		// "###,###,###"));
		// setStrDepositAmount(AecNumberUtils.decimalFormat(getDepositAmount(),
		// "###,###,###"));
		// setStrOtherDepositAmount(AecNumberUtils.decimalFormat(getOtherDepositAmount(),
		// "###,###,###"));
		// setStrSalesAmount(AecNumberUtils.decimalFormat(getSalesAmount(),
		// "###,###,###"));
		// setStrReturnedAmount(AecNumberUtils.decimalFormat(getReturnedAmount(),
		// "###,###,###"));
		// setStrDiscountAmount(AecNumberUtils.decimalFormat(getDiscountAmount(),
		// "###,###,###"));
		// setStrOtherSalesAmount(AecNumberUtils.decimalFormat(getOtherSalesAmount(),
		// "###,###,###"));
		// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrCreditSalesBreakdown(AecNumberUtils.decimalFormat(getCreditSalesBreakdown(),
		// "###,###,###"));
		// setStrAccruedDebitBreakdown(AecNumberUtils.decimalFormat(getAccruedDebitBreakdown(),
		// "###,###,###"));
		// setStrCreditAmount(AecNumberUtils.decimalFormat(getCreditAmount(),
		//			"###,###,###"));

		// 一覧部分
		// setStrRowNo(AecNumberUtils.decimalFormat(getRowNo(), "###"));
		// setStrSales(AecNumberUtils.decimalFormat(getSales(), "###,###,###"));
		// setStrCredit(AecNumberUtils.decimalFormat(getCredit(),
		// "###,###,###"));
		// setStrBalance(AecNumberUtils.decimalFormat(getBalance(),
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
	 * 入金金額を取得します。
	 * @return strDepositAmount
	 */
	public String getStrDepositAmount() {
		return strDepositAmount;
	}

	/**
	 * 入金金額を設定します。
	 * @param strDepositAmount 入金金額
	 */
	public void setStrDepositAmount(final String strDepositAmount) {
		this.strDepositAmount = strDepositAmount;
	}

	/**
	 * その他入金金額を取得します。
	 * @return strOtherDepositAmount
	 */
	public String getStrOtherDepositAmount() {
		return strOtherDepositAmount;
	}

	/**
	 * その他入金金額を設定します。
	 * @param strOtherDepositAmount その他入金金額
	 */
	public void setStrOtherDepositAmount(final String strOtherDepositAmount) {
		this.strOtherDepositAmount = strOtherDepositAmount;
	}

	/**
	 * 売上高を取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 売上高を設定します。
	 * @param strSalesAmount 売上高
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * 返品金額を取得します。
	 * @return strReturnedAmount
	 */
	public String getStrReturnedAmount() {
		return strReturnedAmount;
	}

	/**
	 * 返品金額を設定します。
	 * @param strReturnedAmount 返品金額
	 */
	public void setStrReturnedAmount(final String strReturnedAmount) {
		this.strReturnedAmount = strReturnedAmount;
	}

	/**
	 * 値引金額を取得します。
	 * @return strDiscountAmount
	 */
	public String getStrDiscountAmount() {
		return strDiscountAmount;
	}

	/**
	 * 値引金額を設定します。
	 * @param strDiscountAmount 値引金額
	 */
	public void setStrDiscountAmount(final String strDiscountAmount) {
		this.strDiscountAmount = strDiscountAmount;
	}

	/**
	 * その他売上金額を取得します。
	 * @return strOtherSalesAmount
	 */
	public String getStrOtherSalesAmount() {
		return strOtherSalesAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param strOtherSalesAmount その他売上金額
	 */
	public void setStrOtherSalesAmount(final String strOtherSalesAmount) {
		this.strOtherSalesAmount = strOtherSalesAmount;
	}

	/**
	 * 相殺金額を取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param strOffsetAmount 相殺金額
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
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
	public void setStrAccruedDebitBreakdown(final String strAccruedDebitBreakdown) {
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
	 * 残高を取得します。
	 * @return strBalance
	 */
	public String getStrBalance() {
		return strBalance;
	}

	/**
	 * 残高を設定します。
	 * @param strBalance 残高
	 */
	public void setStrBalance(final String strBalance) {
		this.strBalance = strBalance;
	}

	/**
	 * 入金額を取得します。
	 * @return strCredit
	 */
	public String getStrCredit() {
		return strCredit;
	}

	/**
	 * 入金額を設定します。
	 * @param strCredit 入金額
	 */
	public void setStrCredit(final String strCredit) {
		this.strCredit = strCredit;
	}

	/**
	 * 行番号を取得します。
	 * @return strRowNo
	 */
	public String getStrRowNo() {
		return strRowNo;
	}

	/**
	 * 行番号を設定します。
	 * @param strRowNo 行番号
	 */
	public void setStrRowNo(final String strRowNo) {
		this.strRowNo = strRowNo;
	}

	/**
	 * 売上高を取得します。
	 * @return strSales
	 */
	public String getStrSales() {
		return strSales;
	}

	/**
	 * 売上高を設定します。
	 * @param strSales 売上高
	 */
	public void setStrSales(final String strSales) {
		this.strSales = strSales;
	}

}

