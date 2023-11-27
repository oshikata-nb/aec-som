/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdelivery;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先検索(ポップアップ)検索条件
 * @author tosco 
 */
public class OrderDeliverySearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OrderDeliverySearchListPagerCondition() {
	}

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：区分 */
	private String srhDivision;

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
	 * 検索入力.区分取得
	 * @return String 区分
	 */
	public String getSrhDivision() {
		return this.srhDivision;
	}

	/**
	 * 検索入力.区分設定
	 * * @param srhDivision 区分
	 */
	public void setSrhDivision(final String srhDivision) {
		this.srhDivision = srhDivision;
	}

}
