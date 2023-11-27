/*
 * Created on 2009/10/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplan;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DepositPlanListクラス.
 * @author t0011036
 */
public class DepositPlanListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DepositPlanListBase() {
	}

	//
	// 定数
	//

	/* 請求番号 */
	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/* 部署コード */
	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/* 部署名称 */
	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/* 請求先コード */
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/* 請求先名称 */
	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/* 請求日付 */
	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/* 入金予定日 */
	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/* 請求額(今回請求額-差引繰越額) */
	/** COLUMNアノテーション creditScheduledDate */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/* 入金分類 */
	/** COLUMNアノテーション claimAmountBalance */
	public static final String claimAmountBalance_COLUMN = "CLAIM_AMOUNT_BALANCE";

	/* 手形サイト */
	/** COLUMNアノテーション creditDivision */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/* 入金名称 */
	/** COLUMNアノテーション noteSight */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/*  */
	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/*  */
	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/*  */
	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/*  */
	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/*  */
	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	//
	// インスタンスフィールド
	//

	/** 請求番号 */
	private String claimNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName1;

	/** 請求日付 */
	private String venderShortedName;

	/** 入金予定日 */
	private String creditDate;

	/** 請求額(今回請求額-差引繰越額) */
	private String creditScheduledDate;

	/** 入金分類 */
	private java.math.BigDecimal claimAmountBalance;

	/** 手形サイト */
	private String creditDivision;

	/** 入金名称 */
	private java.math.BigDecimal noteSight;

	/**  */
	private String categoryName;

	/**  */
	private String bankMasterCd;

	/**  */
	private String bankMasterName;

	/**  */
	private String accountDivision;

	/**  */
	private String accountNo;

	//
	// インスタンスメソッド
	//

	/**
	 * claimNo取得.
	 * 請求番号
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 * 請求番号
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * organizationCd取得.
	 * 部署コード
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * 部署コード
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * 部署名称
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * 部署名称
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * venderCd取得.
	 * 請求先コード
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * 請求先コード
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1取得.
	 * 請求先名称
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * 請求先名称
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedName取得.
	 * 請求日付
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return this.venderShortedName;
	}

	/**
	 * venderShortedName設定.
	 * 請求日付
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * creditDate取得.
	 * 入金予定日
	 * @return creditDate
	 */
	public String getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 * 入金予定日
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final String creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * creditScheduledDate取得.
	 * 請求額(今回請求額-差引繰越額)
	 * @return creditScheduledDate
	 */
	public String getCreditScheduledDate() {
		return this.creditScheduledDate;
	}

	/**
	 * creditScheduledDate設定.
	 * 請求額(今回請求額-差引繰越額)
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final String creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * claimAmountBalance取得.
	 * 入金分類
	 * @return claimAmountBalance
	 */
	public java.math.BigDecimal getClaimAmountBalance() {
		return this.claimAmountBalance;
	}

	/**
	 * claimAmountBalance設定.
	 * 入金分類
	 * @param claimAmountBalance claimAmountBalance
	 */
	public void setClaimAmountBalance(final java.math.BigDecimal claimAmountBalance) {
		this.claimAmountBalance = claimAmountBalance;
	}

	/**
	 * creditDivision取得.
	 * 手形サイト
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 * 手形サイト
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * noteSight取得.
	 * 入金名称
	 * @return noteSight
	 */
	public java.math.BigDecimal getNoteSight() {
		return this.noteSight;
	}

	/**
	 * noteSight設定.
	 * 入金名称
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final java.math.BigDecimal noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * categoryName取得.
	 * 
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * 
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * bankMasterCd取得.
	 * 
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * bankMasterCd設定.
	 * 
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * bankMasterName取得.
	 * 
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 * 
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * accountDivision取得.
	 * 
	 * @return accountDivision
	 */
	public String getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 * 
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNo取得.
	 * 
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 * 
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

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
}

