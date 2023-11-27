/*
 * Created on 2008/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplan;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 支払予定一覧用Daoクラス.
 * @author tosco
 */
public class PaymentPlanListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.支払予定一覧
	 */
	public PaymentPlanListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション paymentNo */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション creditScheduledDate */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/** COLUMNアノテーション creditDivision */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/** COLUMNアノテーション creditDivisionName */
	public static final String creditDivisionName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション noteSight */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/** COLUMNアノテーション payableAmountBalance */
	public static final String payableAmountBalance_COLUMN = "PAYABLE_AMOUNT_BALANCE";

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション bankName */
	public static final String bankName_COLUMN = "BANK_NAME";

	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	//
	// インスタンスフィールド	//

	//支払番号	private String paymentNo;

	//部署コード
	private String organizationCd;

	//部署名称	private String organizationName;

	//支払先コード
	private String venderCd;

	//支払先名称	private String venderName1;

	// 支払先略称
	private String venderShortedName;

	//支払予定日
	private String creditScheduledDate;

	//支払分類
	private String creditDivision;

	//分類名称
	private String creditDivisionName;

	//手形サイト
	private String noteSight;

	//支払額(支払残高-差引繰越額)
	private BigDecimal payableAmountBalance;

	//銀行＋支店コード
	private String bankCd;

	//銀行＋支店名称
	private String bankName;

	//預金種別
	private String accountDivision;

	//口座番号
	private String accountNo;


	//
	// インスタンスメソッド
	//

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * accountDivisionを取得します。
	 * @return accountDivision
	 */
	public String getAccountDivision() {
		return accountDivision;
	}

	/**
	 * accountDivisionを設定します。
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNoを取得します。
	 * @return accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * accountNoを設定します。
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * bankCdを取得します。
	 * @return bankCd
	 */
	public String getBankCd() {
		return bankCd;
	}

	/**
	 * bankCdを設定します。
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * bankNameを取得します。
	 * @return bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * bankNameを設定します。
	 * @param bankName bankName
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	/**
	 * creditDivisionを取得します。
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return creditDivision;
	}

	/**
	 * creditDivisionを設定します。
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * creditDivisionNameを取得します。
	 * @return creditDivisionName
	 */
	public String getCreditDivisionName() {
		return creditDivisionName;
	}

	/**
	 * creditDivisionNameを設定します。
	 * @param creditDivisionName creditDivisionName
	 */
	public void setCreditDivisionName(final String creditDivisionName) {
		this.creditDivisionName = creditDivisionName;
	}

	/**
	 * creditScheduledDateを取得します。
	 * @return creditScheduledDate
	 */
	public String getCreditScheduledDate() {
		return creditScheduledDate;
	}

	/**
	 * creditScheduledDateを設定します。
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final String creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * noteSightを取得します。
	 * @return noteSight
	 */
	public String getNoteSight() {
		return noteSight;
	}

	/**
	 * noteSightを設定します。
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final String noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * payableAmountを取得します。
	 * @return payableAmountBalance
	 */
	public BigDecimal getPayableAmountBalance() {
		return payableAmountBalance;
	}

	/**
	 * payableAmountを設定します。
	 * @param payableAmountBalance payableAmountBalance
	 */
	public void setPayableAmountBalance(final BigDecimal payableAmountBalance) {
		this.payableAmountBalance = payableAmountBalance;
	}

	/**
	 * paymentNoを取得します。
	 * @return paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * paymentNoを設定します。
	 * @param paymentNo paymentNo
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
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
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}
	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}
}

