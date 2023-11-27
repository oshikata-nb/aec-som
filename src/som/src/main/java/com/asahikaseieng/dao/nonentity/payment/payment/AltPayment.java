/*
 * Created on 2008/08/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 支払トランザクションテーブルデータ
 * @author tosco
 */
public class AltPayment extends AltPaymentBase implements
		PropertyTransferCallbacker {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション paymentDivision */
	public static final String paymentDivision_COLUMN = "PAYMENT_DIVISION";

	/** COLUMNアノテーション paymentDivisionName */
	public static final String paymentDivisionName_COLUMN = "PAYMENT_DIVISION_NAME";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション paymentScheduledDate */
	public static final String paymentScheduledDate_COLUMN = "PAYMENT_SCHEDULED_DATE";

	/** COLUMNアノテーション noteSight */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション accountPayableSum */
	public static final String accountPayableSum_COLUMN = "ACCOUNT_PAYABLE_SUM";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション commission */
	public static final String commission_COLUMN = "COMMISSION";

	/** COLUMNアノテーション purchaseDiscountAmount */
	public static final String purchaseDiscountAmount_COLUMN = "PURCHASE_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション offsetDate */
	public static final String offsetDate_COLUMN = "OFFSET_DATE";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション payableOffsetAmount */
	public static final String payableOffsetAmount_COLUMN = "PAYABLE_OFFSET_AMOUNT";

	/** COLUMNアノテーション totalPayableAmount */
	public static final String totalPayableAmount_COLUMN = "TOTAL_PAYABLE_AMOUNT";

	/** COLUMNアノテーション totalPaymentAmount */
	public static final String totalPaymentAmount_COLUMN = "TOTAL_PAYMENT_AMOUNT";

	/** 支払分類 */
	private String paymentDivision;

	/** 支払分類名称 */
	private String paymentDivisionName;

	/** 支払締日 */
	private Timestamp payableDate;

	private String strPayableDate;

	/** 支払予定日 */
	private Timestamp paymentScheduledDate;

	private String strCreditScheduledDate;

	/** 手形サイト */
	private BigDecimal noteSight;

	/** 差引繰越額 */
	private BigDecimal balanceForward;

	/** 買掛金額合計 */
	private BigDecimal accountPayableSum;

	/** 相殺額 */
	private BigDecimal offsetAmount;

	/** 手数料 */
	private BigDecimal commission;

	/** 仕入割引額 */
	private BigDecimal purchaseDiscountAmount;

	private Boolean checked;

	/** 前月繰越額 */
	private String strBalanceForward;

	/** 今回買掛金合計 */
	private String strAccountPayableSum;

	/** 相殺額 */
	private String strOffsetAmount;

	/** 支払残高 */
	private BigDecimal paymentAmountSum;

	private String strPaymentAmountSum;

	/** 支払予定額 */
	private BigDecimal payableAmount;

	private String strPayableAmount;

	/** 相殺日 */
	private Timestamp offsetDate;

	/** 支払先コード */
	private String venderCd;

	/** 相殺額 */
	private BigDecimal payableOffsetAmount;

	/** 支払残高合計 */
	private BigDecimal totalPayableAmount;

	/** 支払額合計 */
	private BigDecimal totalPaymentAmount;

	/**
	 * コンストラクタ
	 */
	public AltPayment() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);

		setStrPayableDate(AecDateUtils.dateFormat(getPayableDate(),
			"yyyy/MM/dd")); /* 支払締日 */

		setStrCreditScheduledDate(AecDateUtils.dateFormat(
			getCreditScheduledDate(), "yyyy/MM/dd"));

		BigDecimal paymentAmountSumCalc = BigDecimal.ZERO; /* 支払残高 */

		if (getBalanceForward() != null && getAccountPayableSum() != null) {
			paymentAmountSumCalc = getBalanceForward().add(
				getAccountPayableSum());
		}

		setPaymentAmountSum(paymentAmountSumCalc); /* 支払合計 */
	}

	/**
	 * 買掛金額合計取得
	 * @return BigDecimal 買掛金額合計
	 */
	public BigDecimal getAccountPayableSum() {
		return accountPayableSum;
	}

	/**
	 * 買掛金額合計設定
	 * @param accountPayableSum 買掛金額合計
	 */
	public void setAccountPayableSum(final BigDecimal accountPayableSum) {
		this.accountPayableSum = accountPayableSum;
	}

	/**
	 * 差引繰越額取得
	 * @return BigDecimal差引繰越額
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * 差引繰越額設定
	 * @param balanceForward 差引繰越額
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * 支払分類取得
	 * @return String 支払分類
	 */
	public String getPaymentDivision() {
		return paymentDivision;
	}

	/**
	 * 支払分類設定
	 * @param paymentDivision 支払分類
	 */
	public void setPaymentDivision(final String paymentDivision) {
		this.paymentDivision = paymentDivision;
	}

	/**
	 * 支払分類名称取得
	 * @return String 支払分類名称
	 */
	public String getPaymentDivisionName() {
		return paymentDivisionName;
	}

	/**
	 * 支払分類名設定
	 * @param paymentDivisionName 支払分類名称
	 */
	public void setPaymentDivisionName(final String paymentDivisionName) {
		this.paymentDivisionName = paymentDivisionName;
	}

	/**
	 * 手数料取得
	 * @return BigDecimal 手数料
	 */
	public BigDecimal getCommission() {
		return commission;
	}

	/**
	 * 手数料設定
	 * @param commission 手数料
	 */
	public void setCommission(final BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * 相殺額取得
	 * @return BigDecimal 相殺額
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺額設定
	 * @param offsetAmount 相殺額
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 支払予定日取得
	 * @return 支払予定日
	 */
	public Timestamp getPaymentScheduledDate() {
		return paymentScheduledDate;
	}

	/**
	 * 支払予定日設定
	 * @param paymentScheduledDate 支払予定日
	 */
	public void setPaymentScheduledDate(final Timestamp paymentScheduledDate) {
		this.paymentScheduledDate = paymentScheduledDate;
	}

	/**
	 * 仕入割引額取得
	 * @return BigDecimal 仕入割引額
	 */
	public BigDecimal getPurchaseDiscountAmount() {
		return purchaseDiscountAmount;
	}

	/**
	 * 仕入割引額設定
	 * @param purchaseDiscountAmount 仕入割引額
	 */
	public void setPurchaseDiscountAmount(
			final BigDecimal purchaseDiscountAmount) {
		this.purchaseDiscountAmount = purchaseDiscountAmount;
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * strCreditScheduledDateを取得します。
	 * @return strCreditScheduledDate
	 */
	public String getStrCreditScheduledDate() {
		return strCreditScheduledDate;
	}

	/**
	 * strCreditScheduledDateを設定します。
	 * @param strCreditScheduledDate strCreditScheduledDate
	 */
	public void setStrCreditScheduledDate(final String strCreditScheduledDate) {
		this.strCreditScheduledDate = strCreditScheduledDate;
	}

	/**
	 * strAccountPayableSumを取得します。
	 * @return strAccountPayableSum
	 */
	public String getStrAccountPayableSum() {
		return strAccountPayableSum;
	}

	/**
	 * strAccountPayableSumを設定します。
	 * @param strAccountPayableSum strAccountPayableSum
	 */
	public void setStrAccountPayableSum(final String strAccountPayableSum) {
		this.strAccountPayableSum = strAccountPayableSum;
	}

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
	 * strOffsetAmountを取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * strOffsetAmountを設定します。
	 * @param strOffsetAmount strOffsetAmount
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

	/**
	 * strPaymentAmountSumを取得します。
	 * @return strPaymentAmountSum
	 */
	public String getStrPaymentAmountSum() {
		return strPaymentAmountSum;
	}

	/**
	 * strPaymentAmountSumを設定します。
	 * @param strPaymentAmountSum strPaymentAmountSum
	 */
	public void setStrPaymentAmountSum(final String strPaymentAmountSum) {
		this.strPaymentAmountSum = strPaymentAmountSum;
	}

	/**
	 * paymentAmountSumを取得します。
	 * @return paymentAmountSum
	 */
	public BigDecimal getPaymentAmountSum() {
		return paymentAmountSum;
	}

	/**
	 * paymentAmountSumを設定します。
	 * @param paymentAmountSum paymentAmountSum
	 */
	public void setPaymentAmountSum(final BigDecimal paymentAmountSum) {
		this.paymentAmountSum = paymentAmountSum;
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
	 * payableAmountを取得します。
	 * @return payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * payableAmountを設定します。
	 * @param payableAmount payableAmount
	 */
	public void setPayableAmount(final BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * payableDateを取得します。
	 * @return payableDate
	 */
	public Timestamp getPayableDate() {
		return payableDate;
	}

	/**
	 * payableDateを設定します。
	 * @param payableDate payableDate
	 */
	public void setPayableDate(final Timestamp payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * strPayableDateを取得します。
	 * @return strPayableDate
	 */
	public String getStrPayableDate() {
		return strPayableDate;
	}

	/**
	 * strPayableDateを設定します。
	 * @param strPayableDate strPayableDate
	 */
	public void setStrPayableDate(final String strPayableDate) {
		this.strPayableDate = strPayableDate;
	}

	/**
	 * noteSightを取得します。
	 * @return noteSight
	 */
	public BigDecimal getNoteSight() {
		return noteSight;
	}

	/**
	 * noteSightを設定します。
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final BigDecimal noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * offsetDateを取得します。
	 * @return offsetDate
	 */
	public Timestamp getOffsetDate() {
		return offsetDate;
	}

	/**
	 * offsetDateを設定します。
	 * @param offsetDate offsetDate
	 */
	public void setOffsetDate(final Timestamp offsetDate) {
		this.offsetDate = offsetDate;
	}

	/**
	 * payableOffsetAmountを取得します。
	 * @return payableOffsetAmount
	 */
	public BigDecimal getPayableOffsetAmount() {
		return payableOffsetAmount;
	}

	/**
	 * payableOffsetAmountを設定します。
	 * @param payableOffsetAmount payableOffsetAmount
	 */
	public void setPayableOffsetAmount(final BigDecimal payableOffsetAmount) {
		this.payableOffsetAmount = payableOffsetAmount;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * totalPayableAmountを取得します。
	 * @return totalPayableAmount
	 */
	public BigDecimal getTotalPayableAmount() {
		return totalPayableAmount;
	}

	/**
	 * totalPayableAmountを設定します。
	 * @param totalPayableAmount totalPayableAmount
	 */
	public void setTotalPayableAmount(final BigDecimal totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	/**
	 * totalPaymentAmountを取得します。
	 * @return totalPaymentAmount
	 */
	public BigDecimal getTotalPaymentAmount() {
		return totalPaymentAmount;
	}

	/**
	 * totalPaymentAmountを設定します。
	 * @param totalPaymentAmount totalPaymentAmount
	 */
	public void setTotalPaymentAmount(final BigDecimal totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}
}
