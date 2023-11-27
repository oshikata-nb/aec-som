/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalance;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 支払残高一覧クラス.
 * @author tosco
 */
public class PaymentBalanceList extends PaymentBalanceListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	//前回支払予定額
	private String strClaimAmountForward;

	//支払・その他計
	private String strCreditAmount;

	//今回仕入額
	private String strStockingAmount;

	//その他計
	private String strOtherTotal;

	//消費税
	private String strTaxAmount;

	//支払残高
	private String strPayableAmount;

	/**
	 * コンストラクタ.支払残高一覧
	 */
	public PaymentBalanceList() {
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
		// setStrCreditAmount(AecNumberUtils.decimalFormat(getCreditAmount(),
		// "###,###,###"));
		// setStrStockingAmount(AecNumberUtils.decimalFormat(getStockingAmount(),
		// "###,###,###"));
		// setStrOtherTotal(AecNumberUtils.decimalFormat(getOtherTotal(),
		// "###,###,###"));
		// setStrTaxAmount(AecNumberUtils.decimalFormat(getTaxAmount(),
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
	 * 前回支払予定額を取得します。
	 * @return strClaimAmountForward
	 */
	public String getStrClaimAmountForward() {
		return strClaimAmountForward;
	}

	/**
	 * 前回支払予定額を設定します。
	 * @param strClaimAmountForward 前回支払予定額
	 */
	public void setStrClaimAmountForward(final String strClaimAmountForward) {
		this.strClaimAmountForward = strClaimAmountForward;
	}

	/**
	 * その他計を取得します。
	 * @return strOtherTotal
	 */
	public String getStrOtherTotal() {
		return strOtherTotal;
	}

	/**
	 * その他計を設定します。
	 * @param strOtherTotal その他計
	 */
	public void setStrOtherTotal(final String strOtherTotal) {
		this.strOtherTotal = strOtherTotal;
	}

	/**
	 * 支払残高を取得します。
	 * @return strPayableAmount
	 */
	public String getStrPayableAmount() {
		return strPayableAmount;
	}

	/**
	 * 支払残高を設定します。
	 * @param strPayableAmount 支払残高
	 */
	public void setStrPayableAmount(final String strPayableAmount) {
		this.strPayableAmount = strPayableAmount;
	}

	/**
	 * 今回仕入額を取得します。
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 今回仕入額を設定します。
	 * @param strStockingAmount 今回仕入額
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
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
	 * 支払・その他計を取得します。
	 * @return strCreditAmount
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 支払・その他計を設定します。
	 * @param strCreditAmount 支払・その他計
	 */
	public void setStrCreditAmount(final String strCreditAmount) {
		this.strCreditAmount = strCreditAmount;
	}

}

