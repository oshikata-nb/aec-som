/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷指図帳票Excel
 * 
 * @author t1344224
 */
public class ShippingListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：出荷番号 */
	private String srhShippingNo;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日TO */
	private String srhScheduledShippingDateTo;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：取引先コード */
	private String srhVenderCd;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：出荷ステータス */
	private String srhShippingStatus;

	/** 検索入力：運送会社コード */
	private String srhCarryCd;

	/** 検索入力：ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：品目 */
	private String srhItemCd;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.出荷番号取得
	 * @return srhShippingNo
	 */
	public String getSrhShippingNo() {
		return this.srhShippingNo;
	}

	/**
	 * 検索入力.出荷番号設定 *
	 * @param srhShippingNo organizationId
	 */
	public void setSrhShippingNo(final String srhShippingNo) {
		this.srhShippingNo = AecTextUtils.likeFilter(srhShippingNo);
	}

	/**
	 * 検索入力.出荷予定日FROM取得
	 * @return srhScheduledShippingDate
	 */
	public String getSrhScheduledShippingDateFrom() {
		return this.srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日設定FROM *
	 * @param srhScheduledShippingDateFrom organizationId
	 */
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日TO取得
	 * @return srhScheduledShippingDate
	 */
	public String getSrhScheduledShippingDateTo() {
		return this.srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.出荷予定日TO設定 *
	 * @param srhScheduledShippingDateTo organizationId
	 */
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.受注番号FROM取得
	 * @return srhOrderNo
	 */
	public String getSrhOrderNoFrom() {
		return this.srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号FROM設定 *
	 * @param srhOrderNoFrom organizationId
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号TO取得
	 * @return srhOrderNoTo(
	 */
	public String getSrhOrderNoTo() {
		return this.srhOrderNoTo;
	}

	/**
	 * 検索入力.受注番号TO設定 *
	 * @param srhOrderNoTo organizationId
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力.取引先コード取得
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.取引先コード設定 *
	 * @param srhVenderCd organizationId
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.納入先コード取得
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力.納入先コード設定 *
	 * @param srhDeliveryCd organizationId
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}

	/**
	 * 検索入力.出荷ステータス取得
	 * @return srhShippingStatus
	 */
	public String getSrhShippingStatus() {
		return this.srhShippingStatus;
	}

	/**
	 * 検索入力.出荷ステータス設定 *
	 * @param srhShippingStatus organizationId
	 */
	public void setSrhShippingStatus(final String srhShippingStatus) {
		this.srhShippingStatus = srhShippingStatus;
	}

	/**
	 * 検索入力.運送会社コード取得
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return this.srhCarryCd;
	}

	/**
	 * 検索入力.運送会社コード設定 *
	 * @param srhCarryCd organizationId
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
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
	 * @param srhItemCd organizationId
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.他社コード1
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード1 *
	 * @param srhOtherCompanyCd1 organizationId
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 検索入力.ロケーションコード取得
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力.ロケーションコード設定 *
	 * @param srhLocationCd organizationId
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}

}
