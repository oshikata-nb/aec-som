/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlist;

import java.util.ArrayList;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OrderListPagerConditionクラス.
 * @author t1344224
 */
public class OrderListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderListPagerCondition() {
	}

	//
	// 受注検索入力用
	//
	/* 検索条件.受注番号From */
	private String srhOrderNoFrom;

	/* 検索条件.受注番号 */
	private String srhOrderNoTo;

	/* 検索条件.受注区分 */
	private java.math.BigDecimal srhOrderDivision;

	/* 検索条件.ステータス */
	private java.math.BigDecimal srhStatus;

	/* 検索条件.受注日From */
	private String srhOrderDateFrom;

	/* 検索条件.受注日To */
	private String srhOrderDateTo;

	/* 検索条件.出荷予定日From */
	private String srhScheduledShippingDateFrom;

	/* 検索条件.出荷予定日To */
	private String srhScheduledShippingDateTo;

	/* 検索条件.納入先コード */
	private String srhDeliveryCd;

	/* 検索条件.納入先住所 */
	private String srhDeliveryAddress;

	/* 検索条件.納入先電話番号 */
	private String srhDeliveryTelNo;

	/* 検索条件.得意先コード */
	private String srhVenderCd;

	/* 検索条件.担当部署コード */
	private String srhOrganizationCd;

	/* 検索条件.営業担当者コード */
	private String srhSalesTantoCd;

	/* 検索条件.入力担当者コード */
	private String srhInputTantoCd;

	/* 検索条件品目コード */
	private String srhItemCd;

	/* 検索条件他社コード */
	private String srhOtherCompanyCd1;

	// 2015/5/15 検索条件追加
	private String srhCarryCd;

	// 2015/8/19 検索条件追加
	private ArrayList<String> srhVenderList;

	/**
	 * 検索入力.納入先宛先設定.
	 * @return srhDeliveryAddress
	 */
	public final String getSrhDeliveryAddress() {
		return srhDeliveryAddress;
	}

	/**
	 * 検索入力.納入先宛先取得.
	 * @param srhDeliveryAddress srhDeliveryAddress
	 */
	public final void setSrhDeliveryAddress(final String srhDeliveryAddress) {
		this.srhDeliveryAddress = AecTextUtils.likeFilter(srhDeliveryAddress);
	}

	/**
	 * 検索入力.納入先コード取得
	 * @return srhDeliveryCd
	 */
	public final String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * 検索入力.納入先コード設定.
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public final void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}

	/**
	 * 検索入力.納入先電話番号取得
	 * @return srhDeliveryTelNo
	 */
	public final String getSrhDeliveryTelNo() {
		return srhDeliveryTelNo;
	}

	/**
	 * 検索入力.納入先電話番号設定.
	 * @param srhDeliveryTelNo srhDeliveryTelNo
	 */
	public final void setSrhDeliveryTelNo(final String srhDeliveryTelNo) {
		this.srhDeliveryTelNo = AecTextUtils.likeFilter(srhDeliveryTelNo);
	}

	/**
	 * 検索入力.入力担当者コード取得
	 * @return srhInputTantoCd
	 */
	public final String getSrhInputTantoCd() {
		return srhInputTantoCd;
	}

	/**
	 * 検索入力.入力担当者コード設定.
	 * @param srhInputTantoCd srhInputTantoCd
	 */
	public final void setSrhInputTantoCd(final String srhInputTantoCd) {
		this.srhInputTantoCd = AecTextUtils.likeFilter(srhInputTantoCd);
	}

	/**
	 * 検索入力.品目コード取得
	 * @return srhItemCd
	 */
	public final String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定.
	 * @param srhItemCd srhItemCd
	 */
	public final void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return srhOrganizationCd
	 */
	public final String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定.
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public final void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 検索入力.他社コード１取得
	 * @return srhOtherCompanyCd1
	 */
	public final String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード１設定.
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public final void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.営業担当者コード取得
	 * @return srhSalesTantoCd
	 */
	public final String getSrhSalesTantoCd() {
		return srhSalesTantoCd;
	}

	/**
	 * 検索入力.営業担当者コード設定.
	 * @param srhSalesTantoCd srhSalesTantoCd
	 */
	public final void setSrhSalesTantoCd(final String srhSalesTantoCd) {
		this.srhSalesTantoCd = AecTextUtils.likeFilter(srhSalesTantoCd);
	}

	/**
	 * 検索入力.出荷予定日From取得
	 * @return srhScheduledShippingDateFrom
	 */
	public final String getSrhScheduledShippingDateFrom() {
		return srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日From設定.
	 * @param srhScheduledShippingDateFrom srhScheduledShippingDateFrom
	 */
	public final void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力.出荷予定日To取得
	 * @return srhScheduledShippingDateTo
	 */
	public final String getSrhScheduledShippingDateTo() {
		return srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.出荷予定日To設定.
	 * @param srhScheduledShippingDateTo srhScheduledShippingDateTo
	 */
	public final void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力.ｽﾃｰﾀｽ取得
	 * @return srhStatus
	 */
	public final java.math.BigDecimal getSrhStatus() {
		return srhStatus;
	}

	/**
	 * 検索入力.ｽﾃｰﾀｽ設定.
	 * @param srhStatus srhStatus
	 */
	public final void setSrhStatus(final java.math.BigDecimal srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * 検索入力.受注日From取得
	 * @return srhOrderDateFrom
	 */
	public final String getSrhOrderDateFrom() {
		return srhOrderDateFrom;
	}

	/**
	 * 検索入力.受注日From設定.
	 * @param srhOrderDateFrom srhOrderDateFrom
	 */
	public final void setSrhOrderDateFrom(final String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}

	/**
	 * 検索入力.受注日To取得
	 * @return srhOrderDateTo
	 */
	public final String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}

	/**
	 * 検索入力.注日To設定.
	 * @param srhOrderDateTo srhOrderDateTo
	 */
	public final void setSrhOrderDateTo(final String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
	}

	/**
	 * 検索入力.受注区分取得
	 * @return srhOrderDivision
	 */
	public final java.math.BigDecimal getSrhOrderDivision() {
		return srhOrderDivision;
	}

	/**
	 * 検索入力.受注区分設定.
	 * @param srhOrderDivision srhOrderDivision
	 */
	public final void setSrhOrderDivision(
			final java.math.BigDecimal srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力.受注番号From取得
	 * @return srhOrderNoFrom
	 */
	public final String getSrhOrderNoFrom() {
		return srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号From設定.
	 * @param srhOrderNoFrom srhOrderNoFrom
	 */
	public final void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号To取得
	 * @return srhOrderNoTo
	 */
	public final String getSrhOrderNoTo() {
		return srhOrderNoTo;
	}

	/**
	 * 検索入力.受注番号To設定.
	 * @param srhOrderNoTo srhOrderNoTo
	 */
	public final void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力.取引先コード取得
	 * @return srhVenderCd
	 */
	public final String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力.取引先コード設定.
	 * @param srhVenderCd srhVenderCd
	 */
	public final void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * srhCarryCdを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * srhCarryCdを設定します。
	 * @param srhCarryCd srhCarryCd
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * srhVenderListを取得します。
	 * @return srhVenderList
	 */
	public ArrayList<String> getSrhVenderList() {
		return srhVenderList;
	}

	/**
	 * srhVenderListを設定します。
	 * @param srhVenderList srhVenderList
	 */
	public void setSrhVenderList(final ArrayList<String> srhVenderList) {
		this.srhVenderList = srhVenderList;
	}
}
