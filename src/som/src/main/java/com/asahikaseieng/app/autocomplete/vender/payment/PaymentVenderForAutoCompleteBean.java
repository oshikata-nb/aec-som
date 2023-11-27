/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.payment;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 支払入力－取引マスタのAutoComplete結果表示用Bean
 * @author tosco
 */
public class PaymentVenderForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 支払予定日 */
	private String strPaymentScheduledDate;

	/** 支払方法コード */
	private String categoryDivision;

	/** 支払方法 */
	private String paymentMethod;

	/** 繰越額 */
	private String strCarryoverAmount;

	/** 今回買掛金合計 */
	private String strAccountPayableSum;

	/** 相殺額 */
	private String strOffsetAmount;

	/** 支払合計 */
	private String strPaymentAmountSum;

	/** 仕入割引額 */
	private String strPurchaseDiscountAmount;

	/** 手数料 */
	private String strCommission;

	/** 支払予定額 */
	private String strPaymentScheduledAmount;

	/**
	 * コンストラクタ
	 */
	public PaymentVenderForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public PaymentVenderForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

	/**
	 * 支払方法取得
	 * @return String 支払方法
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * 支払方法設定
	 * @param paymentMethod 支払方法
	 */
	public void setPaymentMethod(final String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 買掛金合計取得
	 * @return String 買掛金合計
	 */
	public String getStrAccountPayableSum() {
		return strAccountPayableSum;
	}

	/**
	 * 買掛金合計設定
	 * @param strAccountPayableSum 買掛金合計
	 */
	public void setStrAccountPayableSum(final String strAccountPayableSum) {
		this.strAccountPayableSum = strAccountPayableSum;
	}

	/**
	 * 繰越額取得
	 * @return String 繰越額
	 */
	public String getStrCarryoverAmount() {
		return strCarryoverAmount;
	}

	/**
	 * 繰越額設定
	 * @param strCarryoverAmount 繰越額
	 */
	public void setStrCarryoverAmount(final String strCarryoverAmount) {
		this.strCarryoverAmount = strCarryoverAmount;
	}

	/**
	 * 手数料取得
	 * @return String手数料
	 */
	public String getStrCommission() {
		return strCommission;
	}

	/**
	 * 手数料設定
	 * @param strCommission 手数料
	 */
	public void setStrCommission(final String strCommission) {
		this.strCommission = strCommission;
	}

	/**
	 * 相殺額取得
	 * @return String 相殺額
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * 相殺額設定
	 * @param strOffsetAmount 相殺額
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

	/**
	 * 支払合計取得
	 * @return String 支払合計
	 */
	public String getStrPaymentAmountSum() {
		return strPaymentAmountSum;
	}

	/**
	 * 支払合計設定
	 * @param strPaymentAmountSum 支払合計
	 */
	public void setStrPaymentAmountSum(final String strPaymentAmountSum) {
		this.strPaymentAmountSum = strPaymentAmountSum;
	}

	/**
	 * 支払予定額取得
	 * @return String 支払予定額
	 */
	public String getStrPaymentScheduledAmount() {
		return strPaymentScheduledAmount;
	}

	/**
	 * 支払予定額設定
	 * @param strPaymentScheduledAmount 支払予定額
	 */
	public void setStrPaymentScheduledAmount(
			final String strPaymentScheduledAmount) {
		this.strPaymentScheduledAmount = strPaymentScheduledAmount;
	}

	/**
	 * 支払予定日取得
	 * @return strPaymentScheduledDate
	 */
	public String getStrPaymentScheduledDate() {
		return strPaymentScheduledDate;
	}

	/**
	 * 支払予定日設定
	 * @param strPaymentScheduledDate strPaymentScheduledDate
	 */
	public void setStrPaymentScheduledDate(final String strPaymentScheduledDate) {
		this.strPaymentScheduledDate = strPaymentScheduledDate;
	}

	/**
	 * 仕入割引額取得
	 * @return String 仕入割引額
	 */
	public String getStrPurchaseDiscountAmount() {
		return strPurchaseDiscountAmount;
	}

	/**
	 * 仕入割引額設定
	 * @param strPurchaseDiscountAmount 仕入割引額
	 */
	public void setStrPurchaseDiscountAmount(
			final String strPurchaseDiscountAmount) {
		this.strPurchaseDiscountAmount = strPurchaseDiscountAmount;
	}

	/**
	 * categoryDivisionを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * categoryDivisionを設定します。
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}
}
