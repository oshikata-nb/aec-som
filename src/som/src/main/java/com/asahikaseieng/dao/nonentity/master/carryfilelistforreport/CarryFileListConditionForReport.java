/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfilelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CarryListPagerConditionクラス.
 * @author kiguchi
 */
public class CarryFileListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 運送会社コード */
	private String srhCarryCd;

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
		this.srhCarryCd = AecTextUtils.likeFilter(srhCarryCd);
	}
}
