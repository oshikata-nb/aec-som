/*
 * Created on 2008/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 買掛元帳用Daoクラス.
 * @author tosco
 */
public class ApLedgerList extends ApLedgerListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 前月買掛残 */
	private String strBalanceForward;

	/** 支払・その他計（支払金額＋その他支払金額） */
	private String strOtherPayment;

	/** 仕入金額 */
	private String strStockingAmount;

	/** その他計（返品金額＋値引金額＋その他仕入金額＋相殺金額） */
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
	public ApLedgerList() {
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
		// setStrOtherPayment(AecNumberUtils.decimalFormat(getOtherPayment(),
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
	 * 前月買掛残を取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * 前月買掛残を設定します。
	 * @param strBalanceForward 前月買掛残
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * その他計を取得します。
	 * @return strOtherStocking
	 */
	public String getStrOtherStocking() {
		return strOtherStocking;
	}

	/**
	 * その他計を設定します。
	 * @param strOtherStocking その他計
	 */
	public void setStrOtherStocking(final String strOtherStocking) {
		this.strOtherStocking = strOtherStocking;
	}

	/**
	 * 仕入金額を取得します。
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 仕入金額を設定します。
	 * @param strStockingAmount 仕入金額
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * 支払・その他計を取得します。
	 * @return strOtherPayment
	 */
	public String getStrOtherPayment() {
		return strOtherPayment;
	}

	/**
	 * 支払・その他計を設定します。
	 * @param strOtherPayment 支払・その他計
	 */
	public void setStrOtherPayment(final String strOtherPayment) {
		this.strOtherPayment = strOtherPayment;
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

}

