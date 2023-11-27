/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.deposit;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * CreditPagerConditionクラス.入金トランザクションテーブル検索条件＆ページャ
 * @author tosco
 */
public class DepositCreditListPagerCondition extends DefaultThresholdPagerCondition {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	// 検索条件
	/** ﾃﾞｰﾀ種別 2:入金（’2’固定） */
	private int dataType;

	/** 部署コード */
	private String organizationCd;

	/** 担当者コード */
	private String tantoCd;

	/** 入金日付FROM */
	private String creditDateFrom;

	/** 入金日付TO */
	private String creditDateTo;

	/** 請求先コード */
	private String venderCd;

	/** 入金番号FROM */
	private String creditNoFrom;

	/** 入金番号TO */
	private String creditNoTo;

	/** 分類コード */
	private String categoryDivision;

	/** 承認ステータス(1：入力中、2：承認依頼中、3：承認済み) */
	private String approvalStatus;

	/** 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
	private String issuedDivision;

	/**
	 * コンストラクタ
	 */
	public DepositCreditListPagerCondition() {
	}

	// 自動生成setter,getter----------------------------------------------------
	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * creditDateFromを取得します。
	 * @return creditDateFrom
	 */
	public String getCreditDateFrom() {
		return creditDateFrom;
	}

	/**
	 * creditDateFromを設定します。
	 * @param creditDateFrom creditDateFrom
	 */
	public void setCreditDateFrom(final String creditDateFrom) {
		this.creditDateFrom = creditDateFrom;
	}

	/**
	 * creditDateToを取得します。
	 * @return creditDateTo
	 */
	public String getCreditDateTo() {
		return creditDateTo;
	}

	/**
	 * creditDateToを設定します。
	 * @param creditDateTo creditDateTo
	 */
	public void setCreditDateTo(final String creditDateTo) {
		this.creditDateTo = creditDateTo;
	}

	/**
	 * creditNoFromを取得します。
	 * @return creditNoFrom
	 */
	public String getCreditNoFrom() {
		return creditNoFrom;
	}

	/**
	 * creditNoFromを設定します。
	 * @param creditNoFrom creditNoFrom
	 */
	public void setCreditNoFrom(final String creditNoFrom) {
		this.creditNoFrom = creditNoFrom;
	}

	/**
	 * creditNoToを取得します。
	 * @return creditNoTo
	 */
	public String getCreditNoTo() {
		return creditNoTo;
	}

	/**
	 * creditNoToを設定します。
	 * @param creditNoTo creditNoTo
	 */
	public void setCreditNoTo(final String creditNoTo) {
		this.creditNoTo = creditNoTo;
	}

	/**
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public int getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final int dataType) {
		this.dataType = dataType;
	}

	/**
	 * issuedDivisionを取得します。
	 * @return issuedDivision
	 */
	public String getIssuedDivision() {
		return issuedDivision;
	}

	/**
	 * issuedDivisionを設定します。
	 * @param issuedDivision issuedDivision
	 */
	public void setIssuedDivision(final String issuedDivision) {
		this.issuedDivision = issuedDivision;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = AecTextUtils.likeFilter(tantoCd);
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
		this.organizationCd = AecTextUtils.likeFilter(organizationCd);
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
		this.venderCd = AecTextUtils.likeFilter(venderCd);
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
