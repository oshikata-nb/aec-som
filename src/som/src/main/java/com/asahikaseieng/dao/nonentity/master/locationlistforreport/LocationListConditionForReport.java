/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.locationlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * LocationPagerConditionクラス.
 * @author jbd
 */
public class LocationListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LocationListConditionForReport() {
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
