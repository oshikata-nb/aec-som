/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplanforreport;

import java.util.Date;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * BalancePagerConditionクラス.入金予定一覧
 * @author tosco
 */
public class DepositPlanListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DepositPlanListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 担当者コード */
	private String srhTantoCd;

	/** 検索入力：出力区分 */
	private String srhOutputDivision;

	/** 検索入力：請求先コード */
	private String srhVenderCd;

	/** 検索入力：請求日付FROM */
	private Date srhCreditDateFrom;

	/** 検索入力：請求日付TO */
	private Date srhCreditDateTo;

	/** 検索入力：入金日付FROM */
	private Date srhCreditScheduledDateFrom;

	/** 検索入力：入金日付TO */
	private Date srhCreditScheduledDateTo;

	/** 検索入力：銀行マスタコード */
	private String srhBankMasterCd;

	/** 検索入力：入金区分 */
	private String srhCreditDivision;

	/**
	 * 担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 担当者コードを設定します。
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 請求先コードを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param srhVenderCd 請求先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * srhCreditDateFromを取得します。
	 * @return srhCreditDateFrom
	 */
	public Date getSrhCreditDateFrom() {
		return srhCreditDateFrom;
	}

	/**
	 * srhCreditDateFromを設定します。
	 * @param srhCreditDateFrom srhCreditDateFrom
	 */
	public void setSrhCreditDateFrom(final Date srhCreditDateFrom) {
		this.srhCreditDateFrom = srhCreditDateFrom;
	}

	/**
	 * srhCreditDateToを取得します。
	 * @return srhCreditDateTo
	 */
	public Date getSrhCreditDateTo() {
		return srhCreditDateTo;
	}

	/**
	 * srhCreditDateToを設定します。
	 * @param srhCreditDateTo srhCreditDateTo
	 */
	public void setSrhCreditDateTo(final Date srhCreditDateTo) {
		this.srhCreditDateTo = srhCreditDateTo;
	}

	/**
	 * srhCreditDivisionを取得します。
	 * @return srhCreditDivision
	 */
	public String getSrhCreditDivision() {
		return srhCreditDivision;
	}

	/**
	 * srhCreditDivisionを設定します。
	 * @param srhCreditDivision srhCreditDivision
	 */
	public void setSrhCreditDivision(final String srhCreditDivision) {
		this.srhCreditDivision = srhCreditDivision;
	}

	/**
	 * srhOutputDivisionを取得します。
	 * @return srhOutputDivision
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * srhOutputDivisionを設定します。
	 * @param srhOutputDivision srhOutputDivision
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

	/**
	 * srhBankMasterCdを取得します。
	 * @return srhBankMasterCd
	 */
	public String getSrhBankMasterCd() {
		return srhBankMasterCd;
	}

	/**
	 * srhBankMasterCdを設定します。
	 * @param srhBankMasterCd srhBankMasterCd
	 */
	public void setSrhBankMasterCd(final String srhBankMasterCd) {
		this.srhBankMasterCd = AecTextUtils.likeFilter(srhBankMasterCd);
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * srhCreditScheduledDateFromを取得します。
	 * @return srhCreditScheduledDateFrom
	 */
	public Date getSrhCreditScheduledDateFrom() {
		return srhCreditScheduledDateFrom;
	}

	/**
	 * srhCreditScheduledDateFromを設定します。
	 * @param srhCreditScheduledDateFrom srhCreditScheduledDateFrom
	 */
	public void setSrhCreditScheduledDateFrom(
			final Date srhCreditScheduledDateFrom) {
		this.srhCreditScheduledDateFrom = srhCreditScheduledDateFrom;
	}

	/**
	 * srhCreditScheduledDateToを取得します。
	 * @return srhCreditScheduledDateTo
	 */
	public Date getSrhCreditScheduledDateTo() {
		return srhCreditScheduledDateTo;
	}

	/**
	 * srhCreditScheduledDateToを設定します。
	 * @param srhCreditScheduledDateTo srhCreditScheduledDateTo
	 */
	public void setSrhCreditScheduledDateTo(final Date srhCreditScheduledDateTo) {
		this.srhCreditScheduledDateTo = srhCreditScheduledDateTo;
	}
}
