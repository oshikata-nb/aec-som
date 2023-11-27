/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 受注検索(ポップアップ)検索条件
 * @author tosco
 */
public class ShippingOrderSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ShippingOrderSearchListPagerCondition() {
	}

	/** 検索入力：受注番号 */
	private String srhOrderNo;

	/** 検索入力：受注区分 */
	private String srhOrderDivision;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日TO */
	private String srhScheduledShippingDateTo;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/**
	 * 検索入力.受注番号取得
	 * @return String 受注番号
	 */
	public String getSrhOrderNo() {
		return this.srhOrderNo;
	}

	/**
	 * 検索入力.受注番号設定
	 * * @param srhOrderNo 受注番号
	 */
	public void setSrhOrderNo(final String srhOrderNo) {
		this.srhOrderNo = AecTextUtils.likeFilter(srhOrderNo);
	}

	/**
	 * 検索入力.受注区分取得
	 * @return String 受注区分
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力.受注区分設定
	 * * @param srhOrderDivision 受注区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力.出荷予定日FROM取得
	 * @return String 出荷予定日FROM
	 */
	public String getSrhScheduledShippingDateFrom() {
		return this.srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日FROM設定
	 * * @param srhScheduledShippingDateFrom 出荷予定日FROM
	 */
	public void setSrhScheduledShippingDateFrom(final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日TO取得
	 * @return String 出荷予定日TO
	 */
	public String getSrhScheduledShippingDateTo() {
		return this.srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.出荷予定日TO設定
	 * * @param srhScheduledShippingDateTo 出荷予定日TO
	 */
	public void setSrhScheduledShippingDateTo(final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.得意先コード取得
	 * @return String 得意先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.得意先コード設定
	 * * @param srhVenderCd 得意先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.納入先コード取得
	 * @return String 納入先コード
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力.納入先コード設定
	 * * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}

	/**
	 * 検索入力.品目コードを取得します。
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索入力.品目コードを設定します。
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.他社コード1取得
	 * @return String 他社コード1
	*/
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード1設定
	 * @param srhOtherCompanyCd1 他社コード1
	*/
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

}
