/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * BumonListPagerConditionクラス.
 * @author jbd
 */
public class BumonListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BumonListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：部門コード */
	private String srhSectionCd;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.部門コード取得
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return srhSectionCd;
	}

	/**
	 * 検索入力.部門コード設定.
	 * @param srhSectionCd sectionCd
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = AecTextUtils.likeFilter(srhSectionCd);
	}
}
