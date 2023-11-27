/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companydetail;

/**
 * CompanyDetailクラス.
 * @author t0011036
 */
public class CompanyDetail extends CompanyDetailBase {

	private static final long serialVersionUID = 1L;

	/* 決算月 */
	private String strSettlement;

	/* 締日 */
	private String strClosingDay;

	/* 消費税率 */
	private String strTaxRatio;

	/* 在庫割引率 */
	private String strStockDiscountRate;

	/* 仕入割引率 */
	private String strPurchaseDiscountRate;

	/* パスワード有効期限 */
	private String strPasswordValidTerm;

	/* 開始パスワード有効桁数 */
	private String strPasswordKetaLowerLimit;

	/* 終了パスワード有効桁数 */
	private String strPasswordKetaUpperLimit;

	/* 短プラ+金利 */
	private String strPrime;

	/* 新消費税率文字 */
	private String strNewTaxRatio;

	/* 新消費税率開始日文字 */
	private String strNewTaxApllyDate;

	/**
	 * コンストラクタ.
	 */
	public CompanyDetail() {
		super();
	}

	/**
	 * strClosingDayを取得します。
	 * @return strClosingDay
	 */
	public String getStrClosingDay() {
		return strClosingDay;
	}

	/**
	 * strClosingDayを設定します。
	 * @param strClosingDay strClosingDay
	 */
	public void setStrClosingDay(final String strClosingDay) {
		this.strClosingDay = strClosingDay;
	}

	/**
	 * strPasswordKetaLowerLimitを取得します。
	 * @return strPasswordKetaLowerLimit
	 */
	public String getStrPasswordKetaLowerLimit() {
		return strPasswordKetaLowerLimit;
	}

	/**
	 * strPasswordKetaLowerLimitを設定します。
	 * @param strPasswordKetaLowerLimit strPasswordKetaLowerLimit
	 */
	public void setStrPasswordKetaLowerLimit(
			final String strPasswordKetaLowerLimit) {
		this.strPasswordKetaLowerLimit = strPasswordKetaLowerLimit;
	}

	/**
	 * strPasswordKetaUpperLimitを取得します。
	 * @return strPasswordKetaUpperLimit
	 */
	public String getStrPasswordKetaUpperLimit() {
		return strPasswordKetaUpperLimit;
	}

	/**
	 * strPasswordKetaUpperLimitを設定します。
	 * @param strPasswordKetaUpperLimit strPasswordKetaUpperLimit
	 */
	public void setStrPasswordKetaUpperLimit(
			final String strPasswordKetaUpperLimit) {
		this.strPasswordKetaUpperLimit = strPasswordKetaUpperLimit;
	}

	/**
	 * strPasswordValidTermを取得します。
	 * @return strPasswordValidTerm
	 */
	public String getStrPasswordValidTerm() {
		return strPasswordValidTerm;
	}

	/**
	 * strPasswordValidTermを設定します。
	 * @param strPasswordValidTerm strPasswordValidTerm
	 */
	public void setStrPasswordValidTerm(final String strPasswordValidTerm) {
		this.strPasswordValidTerm = strPasswordValidTerm;
	}

	/**
	 * strPrimeを取得します。
	 * @return strPrime
	 */
	public String getStrPrime() {
		return strPrime;
	}

	/**
	 * strPrimeを設定します。
	 * @param strPrime strPrime
	 */
	public void setStrPrime(final String strPrime) {
		this.strPrime = strPrime;
	}

	/**
	 * strPurchaseDiscountRateを取得します。
	 * @return strPurchaseDiscountRate
	 */
	public String getStrPurchaseDiscountRate() {
		return strPurchaseDiscountRate;
	}

	/**
	 * strPurchaseDiscountRateを設定します。
	 * @param strPurchaseDiscountRate strPurchaseDiscountRate
	 */
	public void setStrPurchaseDiscountRate(final String strPurchaseDiscountRate) {
		this.strPurchaseDiscountRate = strPurchaseDiscountRate;
	}

	/**
	 * strSettlementを取得します。
	 * @return strSettlement
	 */
	public String getStrSettlement() {
		return strSettlement;
	}

	/**
	 * strSettlementを設定します。
	 * @param strSettlement strSettlement
	 */
	public void setStrSettlement(final String strSettlement) {
		this.strSettlement = strSettlement;
	}

	/**
	 * strStockDiscountRateを取得します。
	 * @return strStockDiscountRate
	 */
	public String getStrStockDiscountRate() {
		return strStockDiscountRate;
	}

	/**
	 * strStockDiscountRateを設定します。
	 * @param strStockDiscountRate strStockDiscountRate
	 */
	public void setStrStockDiscountRate(final String strStockDiscountRate) {
		this.strStockDiscountRate = strStockDiscountRate;
	}

	/**
	 * strTaxRatioを取得します。
	 * @return strTaxRatio
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * strTaxRatioを設定します。
	 * @param strTaxRatio strTaxRatio
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}

	/**
	 * strNewTaxRatioを取得します。
	 * @return strNewTaxRatio
	 */
	public String getStrNewTaxRatio() {
		return strNewTaxRatio;
	}

	/**
	 * strNewTaxRatioを設定します。
	 * @param strNewTaxRatio strNewTaxRatio
	 */
	public void setStrNewTaxRatio(final String strNewTaxRatio) {
		this.strNewTaxRatio = strNewTaxRatio;
	}

	/**
	 * strNewTaxApllyDateを取得します。
	 * @return strNewTaxApllyDate
	 */
	public String getStrNewTaxApllyDate() {
		return strNewTaxApllyDate;
	}

	/**
	 * strNewTaxApllyDateを設定します。
	 * @param strNewTaxApllyDate strNewTaxApllyDate
	 */
	public void setStrNewTaxApllyDate(final String strNewTaxApllyDate) {
		this.strNewTaxApllyDate = strNewTaxApllyDate;
	}
}
