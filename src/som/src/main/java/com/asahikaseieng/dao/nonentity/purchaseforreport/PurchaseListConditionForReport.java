/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchaseforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 発注画面 一覧複数ページ制御クラス.
 * 
 * @author t1344224
 */
public class PurchaseListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：発注日from */
	private String srhOrderDateFrom;

	/** 検索入力：発注日to */
	private String srhOrderDateTo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：発注担当者コード */
	private String srhTantoCd;

	/** 検索入力：オーダー区分 */
	private String srhOrderDivision;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：納品希望日from */
	private String srhSuggestedDeliverlimitDateFrom;

	/** 検索入力：納品希望日to */
	private String srhSuggestedDeliverlimitDateTo;

	/** 検索入力：納入先コード */
	private String srhLocationCd;

	/** 検索入力：購買ステータス */
	private String srhStatus;

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** 検索入力：他社コード */
	private String srhTashaCd;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.発注番号取得
	 * @return srhBuySubcontractOrderNo
	 */
	public String getSrhBuySubcontractOrderNo() {
		return this.srhBuySubcontractOrderNo;
	}

	/**
	 * 検索入力.発注番号設定 *
	 * @param srhBuySubcontractOrderNo 検索入力.発注番号
	 */
	public void setSrhBuySubcontractOrderNo(
			final String srhBuySubcontractOrderNo) {
		this.srhBuySubcontractOrderNo = AecTextUtils
				.likeFilter(srhBuySubcontractOrderNo);
	}

	/**
	 * 検索入力.発注日from取得
	 * @return srhOrderDateFrom
	 */
	public String getSrhOrderDateFrom() {
		return this.srhOrderDateFrom;
	}

	/**
	 * 検索入力.発注日from設定 *
	 * @param srhOrderDateFrom 検索入力.発注日from
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力.発注日to取得
	 * @return srhOrderDateTo
	 */
	public String getSrhOrderDateTo() {
		return this.srhOrderDateTo;
	}

	/**
	 * 検索入力.発注日to設定 *
	 * @param srhOrderDateTo 検索入力.発注日to
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return srhChargeOrganizationCd
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定 *
	 * @param srhChargeOrganizationCd 検索入力.担当部署コード設定
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = AecTextUtils
				.likeFilter(srhChargeOrganizationCd);
	}

	/**
	 * 検索入力.部署コード取得
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力.部署コード設定 *
	 * @param srhOrganizationCd 検索入力.部署コード設定
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 検索入力.発注担当者コード取得
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力.発注担当者コード設定 *
	 * @param srhTantoCd 検索入力.発注担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 検索入力.オーダー区分取得
	 * @return srhOrderDivision
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力.オーダー区分設定 *
	 * @param srhOrderDivision 検索入力.オーダー区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力.仕入先コード
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.仕入先コード *
	 * @param srhVenderCd 検索入力.仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.品目コード取得
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定 *
	 * @param srhItemCd 検索入力.品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.納品希望日from取得
	 * @return srhSuggestedDeliverlimitDateFrom
	 */
	public String getSrhSuggestedDeliverlimitDateFrom() {
		return this.srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力.納品希望日from設定 *
	 * @param srhSuggestedDeliverlimitDateFrom 検索入力.納品希望日
	 */
	public void setSrhSuggestedDeliverlimitDateFrom(
			final String srhSuggestedDeliverlimitDateFrom) {
		this.srhSuggestedDeliverlimitDateFrom = srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力.納品希望日to取得
	 * @return srhSuggestedDeliverlimitDateTo
	 */
	public String getSrhSuggestedDeliverlimitDateTo() {
		return this.srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力.納品希望日to設定 *
	 * @param srhSuggestedDeliverlimitDateTo 検索入力.納品希望日
	 */
	public void setSrhSuggestedDeliverlimitDateTo(
			final String srhSuggestedDeliverlimitDateTo) {
		this.srhSuggestedDeliverlimitDateTo = srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力.納入先コード取得
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力.納入先コード設定 *
	 * @param srhLocationCd 検索入力.納入ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}

	/**
	 * 検索入力.購買ステータス
	 * @return srhStatus
	 */
	public String getSrhStatus() {
		return this.srhStatus;
	}

	/**
	 * 検索入力.購買ステータス *
	 * @param srhStatus organizationId
	 */
	public void setSrhStatus(final String srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * 検索入力.発注書NO取得
	 * @return srhOrderSheetNo
	 */
	public String getSrhOrderSheetNo() {
		return this.srhOrderSheetNo;
	}

	/**
	 * 検索入力.発注書NO設定 *
	 * @param srhOrderSheetNo 検索入力.発注書NO
	 */
	public void setSrhOrderSheetNo(final String srhOrderSheetNo) {
		this.srhOrderSheetNo = AecTextUtils.likeFilter(srhOrderSheetNo);
	}

	/**
	 * 検索入力：他社コードを取得
	 * @return 検索入力：他社コード１
	 */
	public String getSrhTashaCd() {
		return srhTashaCd;
	}

	/**
	 * 検索入力：他社コードを設定
	 * @param srhTashaCd 検索入力：他社コード
	 */
	public void setSrhTashaCd(final String srhTashaCd) {
		this.srhTashaCd = AecTextUtils.likeFilter(srhTashaCd);
	}

}
