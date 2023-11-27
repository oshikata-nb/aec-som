/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * DeliveryListPagerConditionクラス.
 * @author tosco
 */
public class DeliveryListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DeliveryListPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 納入先コード */
	private String srhDeliveryCd;

	/**
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}
}
