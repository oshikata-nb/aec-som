/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalance;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 買掛残高一覧用Daoクラス.
 * @author tosco
 */
public class ApBalanceList extends ApBalanceListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 支払金額(支払金額＋その他支払金額) */
	private String strTotalPayment;

	/** 仕入金額 */
	private String strStockingAmount;

	/** その他計(返品金額＋値引金額＋その他仕入金額＋相殺) */
	private String strOtherStocking;

	/** 消費税額 */
	private String strTaxAmount;

	/** 買掛金(内訳) */
	private String strAccountPayableBreakdouwn;

	/** 未払金(内訳) */
	private String strArrearageBreakdouwn;

	/** 買掛残高 */
	private String strPayableAmount;

	/**
	 * コンストラクタ.
	 */
	public ApBalanceList() {
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
		// setStrTotalPayment(AecNumberUtils.decimalFormat(getTotalPayment(),
		// "###,###,###"));
		// setStrStockingAmount(AecNumberUtils.decimalFormat(getStockingAmount(),
		// "###,###,###"));
		// setStrOtherStocking(AecNumberUtils.decimalFormat(getOtherStocking(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
		// "###,###,###"));
		// setStrAccountPayableBreakdouwn(AecNumberUtils.decimalFormat(getAccountPayableBreakdouwn(),
		// "###,###,###"));
		// setStrArrearageBreakdouwn(AecNumberUtils.decimalFormat(getArrearageBreakdouwn(),
		// "###,###,###"));
		// setStrPayableAmount(AecNumberUtils.decimalFormat(getPayableAmount(),
		//			"###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * strBalanceForwardを取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * strBalanceForwardを設定します。
	 * @param strBalanceForward strBalanceForward
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * strOtherStockingを取得します。
	 * @return strOtherStocking
	 */
	public String getStrOtherStocking() {
		return strOtherStocking;
	}

	/**
	 * strOtherStockingを設定します。
	 * @param strOtherStocking strOtherStocking
	 */
	public void setStrOtherStocking(final String strOtherStocking) {
		this.strOtherStocking = strOtherStocking;
	}

	/**
	 * strPayableAmountを取得します。
	 * @return strPayableAmount
	 */
	public String getStrPayableAmount() {
		return strPayableAmount;
	}

	/**
	 * strPayableAmountを設定します。
	 * @param strPayableAmount strPayableAmount
	 */
	public void setStrPayableAmount(final String strPayableAmount) {
		this.strPayableAmount = strPayableAmount;
	}

	/**
	 * strStockingAmountを取得します。
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * strStockingAmountを設定します。
	 * @param strStockingAmount strStockingAmount
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
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

	/**
	 * strTotalPaymentを取得します。
	 * @return strTotalPayment
	 */
	public String getStrTotalPayment() {
		return strTotalPayment;
	}

	/**
	 * strTotalPaymentを設定します。
	 * @param strTotalPayment strTotalPayment
	 */
	public void setStrTotalPayment(final String strTotalPayment) {
		this.strTotalPayment = strTotalPayment;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return strAccountPayableBreakdouwn
	 */
	public String getStrAccountPayableBreakdouwn() {
		return strAccountPayableBreakdouwn;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param strAccountPayableBreakdouwn 買掛金(内訳)
	 */
	public void setStrAccountPayableBreakdouwn(final String strAccountPayableBreakdouwn) {
		this.strAccountPayableBreakdouwn = strAccountPayableBreakdouwn;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return strArrearageBreakdouwn
	 */
	public String getStrArrearageBreakdouwn() {
		return strArrearageBreakdouwn;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param strArrearageBreakdouwn 未払金(内訳)
	 */
	public void setStrArrearageBreakdouwn(final String strArrearageBreakdouwn) {
		this.strArrearageBreakdouwn = strArrearageBreakdouwn;
	}

}

