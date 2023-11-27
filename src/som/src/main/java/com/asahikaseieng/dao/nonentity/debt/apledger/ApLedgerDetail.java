/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 買掛元帳詳細用クラス.
 * @author tosco
 */
public class ApLedgerDetail extends ApLedgerDetailBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 支払金額 */
	private String strPaymentAmount;

	/** その他支払金額 */
	private String strOtherPaymentAmount;

	/** 仕入高*/
	private String strStockingAmount;

	/** 返品金額 */
	private String strReturnedAmount;

	/** 値引金額 */
	private String strDiscountAmount;

	/** その他仕入金額 */
	private String strOtherStockingAmount;

	/** 相殺金額 */
	private String strOffsetAmount;

	/** 消費税額 */
	private String strTaxAmount;

	/** 買掛残高 */
	private String strPayableAmount;

	/** 買掛金(内訳) */
	private String strAccountPayableBreakdown;

	/** 未払金(内訳) */
	private String strArrearageBreakdown;

	/** 以外(内訳) */
	private String strExceptBreakdown;


	/** 行番号 */
	private String strRowNo;

	/** 仕入金額 */
	private String strStocking;

	/** 支払金額 */
	private String strPayment;

	/** 残高 */
	private String strBalance;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerDetail() {
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
		// setStrPaymentAmount(AecNumberUtils.decimalFormat(getPaymentAmount(),
		// "###,###,###"));
		// setStrOtherPaymentAmount(AecNumberUtils.decimalFormat(
		// getOtherPaymentAmount(), "###,###,###"));
		// setStrStockingAmount(AecNumberUtils.decimalFormat(getStockingAmount(),
		// "###,###,###"));
		// setStrReturnedAmount(AecNumberUtils.decimalFormat(getReturnedAmount(),
		// "###,###,###"));
		// setStrDiscountAmount(AecNumberUtils.decimalFormat(getDiscountAmount(),
		// "###,###,###"));
		// setStrOtherStockingAmount(AecNumberUtils.decimalFormat(
		// getOtherStockingAmount(), "###,###,###"));
		// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrPayableAmount(AecNumberUtils.decimalFormat(getPayableAmount(),
		// "###,###,###"));
		// setStrAccountPayableBreakdown(AecNumberUtils.decimalFormat(
		// getAccountPayableBreakdown(), "###,###,###"));
		// setStrArrearageBreakdown(AecNumberUtils.decimalFormat(
		// getArrearageBreakdown(), "###,###,###"));
		// setStrExceptBreakdown(AecNumberUtils.decimalFormat(
		//			getExceptBreakdown(), "###,###,###"));

		// 一覧部分
		// setStrRowNo(AecNumberUtils.decimalFormat(getRowNo(), "###"));
		// setStrStocking(AecNumberUtils.decimalFormat(getStocking(),
		// "###,###,###"));
		// setStrPayment(AecNumberUtils.decimalFormat(getPayment(),
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
	 * 支払金額を取得します。
	 * @return strPaymentAmount
	 */
	public String getStrPaymentAmount() {
		return strPaymentAmount;
	}

	/**
	 * 支払金額を設定します。
	 * @param strPaymentAmount 支払金額
	 */
	public void setStrPaymentAmount(final String strPaymentAmount) {
		this.strPaymentAmount = strPaymentAmount;
	}

	/**
	 * その他支払金額を取得します。
	 * @return strOtherPaymentAmount
	 */
	public String getStrOtherPaymentAmount() {
		return strOtherPaymentAmount;
	}

	/**
	 * その他支払金額を設定します。
	 * @param strOtherPaymentAmount その他支払金額
	 */
	public void setStrOtherPaymentAmount(final String strOtherPaymentAmount) {
		this.strOtherPaymentAmount = strOtherPaymentAmount;
	}

	/**
	 * 仕入高を取得します。
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 仕入高を設定します。
	 * @param strStockingAmount 仕入高
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
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
	 * @return strOtherStockingAmount
	 */
	public String getStrOtherStockingAmount() {
		return strOtherStockingAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param strOtherStockingAmount その他売上金額
	 */
	public void setStrOtherStockingAmount(final String strOtherStockingAmount) {
		this.strOtherStockingAmount = strOtherStockingAmount;
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
	 * 買掛残高を取得します。
	 * @return strPayableAmount
	 */
	public String getStrPayableAmount() {
		return strPayableAmount;
	}

	/**
	 * 買掛残高を設定します。
	 * @param strPayableAmount 買掛残高
	 */
	public void setStrPayableAmount(final String strPayableAmount) {
		this.strPayableAmount = strPayableAmount;
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
	 * @return strPayment
	 */
	public String getStrPayment() {
		return strPayment;
	}

	/**
	 * 入金額を設定します。
	 * @param strPayment 入金額
	 */
	public void setStrPayment(final String strPayment) {
		this.strPayment = strPayment;
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
	 * @return strStocking
	 */
	public String getStrStocking() {
		return strStocking;
	}

	/**
	 * 売上高を設定します。
	 * @param strStocking 売上高
	 */
	public void setStrStocking(final String strStocking) {
		this.strStocking = strStocking;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return strAccountPayableBreakdown
	 */
	public String getStrAccountPayableBreakdown() {
		return strAccountPayableBreakdown;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param strAccountPayableBreakdown 買掛金(内訳)
	 */
	public void setStrAccountPayableBreakdown(final String strAccountPayableBreakdown) {
		this.strAccountPayableBreakdown = strAccountPayableBreakdown;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return strArrearageBreakdown
	 */
	public String getStrArrearageBreakdown() {
		return strArrearageBreakdown;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param strArrearageBreakdown 未払金(内訳)
	 */
	public void setStrArrearageBreakdown(final String strArrearageBreakdown) {
		this.strArrearageBreakdown = strArrearageBreakdown;
	}

	/**
	 * 以外(内訳)を取得します。
	 * @return strExceptBreakdown
	 */
	public String getStrExceptBreakdown() {
		return strExceptBreakdown;
	}

	/**
	 * 以外(内訳)を設定します。
	 * @param strExceptBreakdown 以外(内訳)
	 */
	public void setStrExceptBreakdown(final String strExceptBreakdown) {
		this.strExceptBreakdown = strExceptBreakdown;
	}

}

