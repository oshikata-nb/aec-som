/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.materialrinputforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 外注原材料投入実績一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class MaterialRinputListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：発注日FROM */
	private String srhOrderDateFrom;

	/** 検索入力：発注日TO */
	private String srhOrderDateTo;

	/** 検索入力：納品希望日FROM */
	private String srhSuggestedDeliverlimitDateFrom;

	/** 検索入力：納品希望日TO */
	private String srhSuggestedDeliverlimitDateTo;

	/** 検索入力：納入ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：発注担当者コード */
	private String srhTantoCd;

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

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
	 * @param srhBuySubcontractOrderNo 発注番号
	 */
	public void setSrhBuySubcontractOrderNo(
			final String srhBuySubcontractOrderNo) {
		this.srhBuySubcontractOrderNo = AecTextUtils
				.likeFilter(srhBuySubcontractOrderNo);
	}

	/**
	 * 検索入力：発注日FROM取得.
	 * @return String 発注日FROM
	 */
	public String getSrhOrderDateFrom() {
		return this.srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日FROM設定.
	 * @param srhOrderDateFrom 発注日FROM
	 */
	public void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力：発注日TO取得.
	 * @return String 発注日TO
	 */
	public String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}

	/**
	 * 検索入力：発注日TO設定.
	 * @param srhOrderDateTo 発注日TO
	 */
	public void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
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
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力：納品希望日FROM取得.
	 * @return String 納品希望日FROM
	 */
	public String getSrhSuggestedDeliverlimitDateFrom() {
		return this.srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日FROM設定.
	 * @param srhSuggestedDeliverlimitDateFrom 納品希望日FROM
	 */
	public void setSrhSuggestedDeliverlimitDateFrom(
			final String srhSuggestedDeliverlimitDateFrom) {
		this.srhSuggestedDeliverlimitDateFrom = srhSuggestedDeliverlimitDateFrom;
	}

	/**
	 * 検索入力：納品希望日TO取得.
	 * @return String 納品希望日TO
	 */
	public String getSrhSuggestedDeliverlimitDateTo() {
		return srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：納品希望日TO設定.
	 * @param srhSuggestedDeliverlimitDateTo 納品希望日TO
	 */
	public void setSrhSuggestedDeliverlimitDateTo(
			final String srhSuggestedDeliverlimitDateTo) {
		this.srhSuggestedDeliverlimitDateTo = srhSuggestedDeliverlimitDateTo;
	}

	/**
	 * 検索入力：他社コード１取得.
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１設定.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 検索入力.納入ロケーションコード取得
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力.納入ロケーションコード設定 *
	 * @param srhLocationCd 納入ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}

	/**
	 * 検索入力.仕入先コード取得
	 * @return String 仕入先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.仕入先コード設定 *
	 * @param srhVenderCd 仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.部署コード取得
	 * @return String 部署コード
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力.部署コード設定 *
	 * @param srhOrganizationCd 部署コード
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
	 * @param srhTantoCd 発注担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
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
	 * @param srhOrderSheetNo 発注書NO
	 */
	public void setSrhOrderSheetNo(final String srhOrderSheetNo) {
		this.srhOrderSheetNo = AecTextUtils.likeFilter(srhOrderSheetNo);
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return String 担当部署コード
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定 *
	 * @param srhChargeOrganizationCd 担当部署コード
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = AecTextUtils
				.likeFilter(srhChargeOrganizationCd);
	}

}
