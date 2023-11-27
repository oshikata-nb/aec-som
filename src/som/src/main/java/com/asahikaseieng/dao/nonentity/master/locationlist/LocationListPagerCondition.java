/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * LocationPagerConditionクラス.
 * @author jbd
 */
public class LocationListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LocationListPagerCondition() {
	}

	//
	// 検索入力用.location
	//

	/** 検索入力：ロケーションコード */
	private String srhLocationCd;

	/**
	 * 検索入力.ロケーションコード取得
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力.ロケーションコード設定.
	 * @param srhLocationCd locationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}
}
