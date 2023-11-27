/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 支払入力－取引先マスタのオートコンプリートデータ格納クラス
 * @author tosco
 */
public class PaymentVenderForAutoComplete extends
		PaymentVenderForAutoCompleteBase implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション paymentScheduledDate */
	public static final String paymentScheduledDate_COLUMN = "PAYMENT_SCHEDULED_DATE";

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

	/** 分類コード */
	private String categoryDivision;

	/** 分類名称 */
	private String categoryName;

	/** 支払予定日 */
	private Timestamp paymentScheduledDate;

	private String strPaymentScheduledDate;

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

	/**
	 * コンストラクタ.
	 */
	public PaymentVenderForAutoComplete() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrPaymentScheduledDate(AecDateUtils.dateFormat(
			getPaymentScheduledDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
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
	 * 分類コード取得
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 分類名称取得
	 * @return String 分類名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名設定
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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
	 * strPaymentScheduledDateを取得します。
	 * @return strPaymentScheduledDate
	 */
	public String getStrPaymentScheduledDate() {
		return strPaymentScheduledDate;
	}

	/**
	 * strPaymentScheduledDateを設定します。
	 * @param strPaymentScheduledDate strPaymentScheduledDate
	 */
	public void setStrPaymentScheduledDate(final String strPaymentScheduledDate) {
		this.strPaymentScheduledDate = strPaymentScheduledDate;
	}
}
