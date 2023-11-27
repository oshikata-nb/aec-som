/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissue;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 請求書発行 一覧画面用Daoクラス.
 * @author tosco
 */
public class BillIssueListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BillIssueListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "CUSTOMER_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション claimAmountForward */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/** COLUMNアノテーション creditAmountForward */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション otherCreditAmountForward */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション salesReturnedAmount */
	public static final String salesReturnedAmount_COLUMN = "SALES_RETURNED_AMOUNT";

	/** COLUMNアノテーション salesDiscountAmount */
	public static final String salesDiscountAmount_COLUMN = "SALES_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherSalesAmount */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション claimAmount */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/** COLUMNアノテーション totalClaimAmount */
	public static final String totalClaimAmount_COLUMN = "TOTAL_CLAIM_AMOUNT";

	/** COLUMNアノテーション otherCreditAmount */
	public static final String otherCreditAmount_COLUMN = "OTHER_CREDIT_AMOUNT";

	/** COLUMNアノテーション otherSales */
	public static final String otherSales_COLUMN = "OTHER_SALES";

	/** COLUMNアノテーション kbn */
	public static final String kbn_COLUMN = "KBN";

	//
	// インスタンスフィールド
	//

	/** 請求番号 */
	private String claimNo;

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求先先略称 */
	private String venderShortedName;

	/** 請求締め日 */
	private String creditDate;

	/** 前回請求額 */
	private BigDecimal claimAmountForward;

	/** 前回入金額 */
	private BigDecimal creditAmountForward;

	/** 前回その他入金額 */
	private BigDecimal otherCreditAmountForward;

	/** 今回売上額 */
	private BigDecimal salesAmount;

	/** 返品金額 */
	private BigDecimal salesReturnedAmount;

	/** 値引金額 */
	private BigDecimal salesDiscountAmount;

	/** その他売上金額 */
	private BigDecimal otherSalesAmount;

	/** 相殺 */
	private BigDecimal offsetAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 今回請求額 */
	private BigDecimal claimAmount;

	/** 請求合計(TS_PTNによって消費税が変わるので計算後の値) */
	private BigDecimal totalClaimAmount;

	/** 画面項目：入金・その他計 */
	private BigDecimal otherCreditAmount;

	/** 画面項目：その他計 */
	private BigDecimal otherSales;

	/** 対象区分 */
	private BigDecimal kbn;

	/** 請求書発行フラグ（チェックボックス） */
	private boolean billIssueFlg;

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
	 * 今回請求額を取得します。
	 * @return 今回請求額
	 */
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	/**
	 * 今回請求額を設定します。
	 * @param claimAmount 今回請求額
	 */
	public void setClaimAmount(final BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * 請求合計を取得します。
	 * @return 請求合計
	 */
	public BigDecimal getTotalClaimAmount() {
		return totalClaimAmount;
	}

	/**
	 * 請求合計を設定します。
	 * @param totalClaimAmount 請求合計
	 */
	public void setTotalClaimAmount(BigDecimal totalClaimAmount) {
		this.totalClaimAmount = totalClaimAmount;
	}

	/**
	 * 前回請求額を取得します。
	 * @return 前回請求額
	 */
	public BigDecimal getClaimAmountForward() {
		return claimAmountForward;
	}

	/**
	 * 前回請求額を設定します。
	 * @param claimAmountForward 前回請求額
	 */
	public void setClaimAmountForward(final BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * 請求番号を取得します。
	 * @return 請求番号
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * 請求番号oを設定します。
	 * @param claimNo 請求番号
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * 前回入金額を取得します。
	 * @return 前回入金額
	 */
	public BigDecimal getCreditAmountForward() {
		return creditAmountForward;
	}

	/**
	 * 前回入金額を設定します。
	 * @param creditAmountForward 前回入金額
	 */
	public void setCreditAmountForward(final BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * 請求締め日を取得します。
	 * @return 請求締め日
	 */
	public String getCreditDate() {
		return creditDate;
	}

	/**
	 * 請求締め日を設定します。
	 * @param creditDate 請求締め日
	 */
	public void setCreditDate(final String creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 相殺を取得します。
	 * @return 相殺
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺を設定します。
	 * @param offsetAmount 相殺
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 画面項目：入金・その他計を取得します。
	 * @return otherCreditAmount
	 */
	public BigDecimal getOtherCreditAmount() {
		return otherCreditAmount;
	}

	/**
	 * 画面項目：入金・その他計を設定します。
	 * @param otherCreditAmount 画面項目：入金・その他計
	 */
	public void setOtherCreditAmount(final BigDecimal otherCreditAmount) {
		this.otherCreditAmount = otherCreditAmount;
	}

	/**
	 * 前回その他入金額を取得します。
	 * @return 前回その他入金額
	 */
	public BigDecimal getOtherCreditAmountForward() {
		return otherCreditAmountForward;
	}

	/**
	 * 前回その他入金額を設定します。
	 * @param otherCreditAmountForward 前回その他入金額
	 */
	public void setOtherCreditAmountForward(
			final BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * 画面項目：その他計を取得します。
	 * @return 画面項目：その他計
	 */
	public BigDecimal getOtherSales() {
		return otherSales;
	}

	/**
	 * 画面項目：その他計を設定します。
	 * @param otherSales 画面項目：その他計
	 */
	public void setOtherSales(final BigDecimal otherSales) {
		this.otherSales = otherSales;
	}

	/**
	 * その他売上金額を取得します。
	 * @return その他売上金額
	 */
	public BigDecimal getOtherSalesAmount() {
		return otherSalesAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param otherSalesAmount その他売上金額
	 */
	public void setOtherSalesAmount(final BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
	}

	/**
	 * 今回売上額を取得します。
	 * @return 今回売上額
	 */
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	/**
	 * 今回売上額を設定します。
	 * @param salesAmount 今回売上額
	 */
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * 値引金額を取得します。
	 * @return 値引金額
	 */
	public BigDecimal getSalesDiscountAmount() {
		return salesDiscountAmount;
	}

	/**
	 * 値引金額を設定します。
	 * @param salesDiscountAmount 値引金額
	 */
	public void setSalesDiscountAmount(final BigDecimal salesDiscountAmount) {
		this.salesDiscountAmount = salesDiscountAmount;
	}

	/**
	 * 返品金額を取得します。
	 * @return 返品金額
	 */
	public BigDecimal getSalesReturnedAmount() {
		return salesReturnedAmount;
	}

	/**
	 * 返品金額を設定します。
	 * @param salesReturnedAmount 返品金額
	 */
	public void setSalesReturnedAmount(final BigDecimal salesReturnedAmount) {
		this.salesReturnedAmount = salesReturnedAmount;
	}

	/**
	 * 部門コードを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コードを設定します。
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称を取得します。
	 * @return 部門名称
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称を設定します。
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 消費税額を取得します。
	 * @return 消費税額
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * 消費税額を設定します。
	 * @param taxAmount 消費税額
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * 請求先コードを取得します。
	 * @return 請求先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param venderCd 請求先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求先名称を取得します。
	 * @return 請求先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称を設定します。
	 * @param venderName 請求先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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

	/**
	 * 請求書発行フラグ（チェックボックス）を取得します。
	 * @return billIssueFlg
	 */
	public boolean isBillIssueFlg() {
		return billIssueFlg;
	}

	/**
	 * 請求書発行フラグ（チェックボックス）を設定します。
	 * @param billIssueFlg 請求書発行フラグ（チェックボックス）
	 */
	public void setBillIssueFlg(final boolean billIssueFlg) {
		this.billIssueFlg = billIssueFlg;
	}

	/**
	 * kbnを取得します。
	 * @return kbn
	 */
	public BigDecimal getKbn() {
		return kbn;
	}

	/**
	 * kbnを設定します。
	 * @param kbn kbn
	 */
	public void setKbn(final BigDecimal kbn) {
		this.kbn = kbn;
	}
}
