/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissuelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * BillIssuePagerConditionクラス.請求書発行
 * @author tosco
 */
public class BillIssueListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BillIssueListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 部門コード */
	private String srhSectionCd;

	/** 担当者コード */
	private String srhTantoCd;

	/** 請求先コード */
	private String srhVenderCd;

	/** 請求締め日 */
	private String srhCreditDate;

	/** 請求番号From */
	private String srhClaimNoFrom;

	/** 請求番号To */
	private String srhClaimNoTo;

	/** 検索入力：請求書発行区分 */
	private boolean srhBillIssueFlg;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/** 検索入力：出力区分 */
	private boolean srhBalanceDivision;

	private boolean srhDealingDivision;

	/**
	 * 部門コードを取得します。
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return srhSectionCd;
	}

	/**
	 * 部門コードを設定します。
	 * @param srhSectionCd 部門コード
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = AecTextUtils.likeFilter(srhSectionCd);
	}

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
	 * 検索入力：通常処理分取得.
	 * @return boolean 通常処理分
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * 検索入力：通常処理分設定
	 * @param srhNormalFlg 通常処理分
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * 検索入力：仮締処理分取得.
	 * @return boolean 仮締処理分
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * 検索入力：仮締処理分設定
	 * @param srhTempClosingFlg 仮締処理分
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}

	/**
	 * srhBillIssueFlgを取得します。
	 * @return srhBillIssueFlg
	 */
	public boolean isSrhBillIssueFlg() {
		return srhBillIssueFlg;
	}

	/**
	 * srhBillIssueFlgを設定します。
	 * @param srhBillIssueFlg srhBillIssueFlg
	 */
	public void setSrhBillIssueFlg(final boolean srhBillIssueFlg) {
		this.srhBillIssueFlg = srhBillIssueFlg;
	}

	/**
	 * srhClaimNoFromを取得します。
	 * @return srhClaimNoFrom
	 */
	public String getSrhClaimNoFrom() {
		return srhClaimNoFrom;
	}

	/**
	 * srhClaimNoFromを設定します。
	 * @param srhClaimNoFrom srhClaimNoFrom
	 */
	public void setSrhClaimNoFrom(final String srhClaimNoFrom) {
		this.srhClaimNoFrom = srhClaimNoFrom;
	}

	/**
	 * srhClaimNoToを取得します。
	 * @return srhClaimNoTo
	 */
	public String getSrhClaimNoTo() {
		return srhClaimNoTo;
	}

	/**
	 * srhClaimNoToを設定します。
	 * @param srhClaimNoTo srhClaimNoTo
	 */
	public void setSrhClaimNoTo(final String srhClaimNoTo) {
		this.srhClaimNoTo = srhClaimNoTo;
	}

	/**
	 * srhCreditDateを取得します。
	 * @return srhCreditDate
	 */
	public String getSrhCreditDate() {
		return srhCreditDate;
	}

	/**
	 * srhCreditDateを設定します。
	 * @param srhCreditDate srhCreditDate
	 */
	public void setSrhCreditDate(final String srhCreditDate) {
		this.srhCreditDate = srhCreditDate;
	}

	/**
	 * srhBalanceDivisionを取得します。
	 * @return srhBalanceDivision
	 */
	public boolean isSrhBalanceDivision() {
		return srhBalanceDivision;
	}

	/**
	 * srhBalanceDivisionを設定します。
	 * @param srhBalanceDivision srhBalanceDivision
	 */
	public void setSrhBalanceDivision(final boolean srhBalanceDivision) {
		this.srhBalanceDivision = srhBalanceDivision;
	}

	/**
	 * srhDealingDivisionを取得します。
	 * @return srhDealingDivision
	 */
	public boolean isSrhDealingDivision() {
		return srhDealingDivision;
	}

	/**
	 * srhDealingDivisionを設定します。
	 * @param srhDealingDivision srhDealingDivision
	 */
	public void setSrhDealingDivision(final boolean srhDealingDivision) {
		this.srhDealingDivision = srhDealingDivision;
	}
}
