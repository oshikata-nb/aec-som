/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatelist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * Conditionクラス.見積ファイル
 * @author T0011036
 */
public class EstimateListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateListPagerCondition() {
	}

	//
	// 検索入力用.getSearchList
	//

	/** 検索入力.見積番号 */
	private String srhEstimateNo;

	/** 検索入力.見積入力日(FROM) */
	private String strSrhEstimateInputDateFrom;

	/** 検索入力.見積入力日(TO) */
	private String strSrhEstimateInputDateTo;

	/** 検索入力.得意先コード */
	private String srhVenderCd;

	/** 検索入力.品目コード */
	private String srhItemCd;

	/** 検索入力.他社コード1 */
	private String srhOtherCompanyCd1;

	/** 見積有効期限(FROM) */
	private String strSrhEstimateValidDateFrom;

	/** 見積有効期限(TO) */
	private String strSrhEstimateValidDateTo;

	/** 見積ステータス */
	private java.math.BigDecimal srhEstimateStatus;

	/**
	 * srhEstimateNoを取得します。
	 * @return srhEstimateNo
	 */
	public String getSrhEstimateNo() {
		return srhEstimateNo;
	}

	/**
	 * srhEstimateNoを設定します。
	 * @param srhEstimateNo srhEstimateNo
	 */
	public void setSrhEstimateNo(final String srhEstimateNo) {
		this.srhEstimateNo = AecTextUtils.likeFilter(srhEstimateNo);
	}

	/**
	 * srhEstimateStatusを取得します。
	 * @return srhEstimateStatus
	 */
	public java.math.BigDecimal getSrhEstimateStatus() {
		return srhEstimateStatus;
	}

	/**
	 * srhEstimateStatusを設定します。
	 * @param srhEstimateStatus srhEstimateStatus
	 */
	public void setSrhEstimateStatus(
			final java.math.BigDecimal srhEstimateStatus) {
		this.srhEstimateStatus = srhEstimateStatus;
	}

	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * strSrhEstimateInputDateFromを取得します。
	 * @return strSrhEstimateInputDateFrom
	 */
	public String getStrSrhEstimateInputDateFrom() {
		return strSrhEstimateInputDateFrom;
	}

	/**
	 * strSrhEstimateInputDateFromを設定します。
	 * @param strSrhEstimateInputDateFrom strSrhEstimateInputDateFrom
	 */
	public void setStrSrhEstimateInputDateFrom(
			final String strSrhEstimateInputDateFrom) {
		this.strSrhEstimateInputDateFrom = strSrhEstimateInputDateFrom;
	}

	/**
	 * strSrhEstimateInputDateToを取得します。
	 * @return strSrhEstimateInputDateTo
	 */
	public String getStrSrhEstimateInputDateTo() {
		return strSrhEstimateInputDateTo;
	}

	/**
	 * strSrhEstimateInputDateToを設定します。
	 * @param strSrhEstimateInputDateTo strSrhEstimateInputDateTo
	 */
	public void setStrSrhEstimateInputDateTo(
			final String strSrhEstimateInputDateTo) {
		this.strSrhEstimateInputDateTo = strSrhEstimateInputDateTo;
	}

	/**
	 * strSrhEstimateValidDateFromを取得します。
	 * @return strSrhEstimateValidDateFrom
	 */
	public String getStrSrhEstimateValidDateFrom() {
		return strSrhEstimateValidDateFrom;
	}

	/**
	 * strSrhEstimateValidDateFromを設定します。
	 * @param strSrhEstimateValidDateFrom strSrhEstimateValidDateFrom
	 */
	public void setStrSrhEstimateValidDateFrom(
			final String strSrhEstimateValidDateFrom) {
		this.strSrhEstimateValidDateFrom = strSrhEstimateValidDateFrom;
	}

	/**
	 * strSrhEstimateValidDateToを取得します。
	 * @return strSrhEstimateValidDateTo
	 */
	public String getStrSrhEstimateValidDateTo() {
		return strSrhEstimateValidDateTo;
	}

	/**
	 * strSrhEstimateValidDateToを設定します。
	 * @param strSrhEstimateValidDateTo strSrhEstimateValidDateTo
	 */
	public void setStrSrhEstimateValidDateTo(
			final String strSrhEstimateValidDateTo) {
		this.strSrhEstimateValidDateTo = strSrhEstimateValidDateTo;
	}
}
