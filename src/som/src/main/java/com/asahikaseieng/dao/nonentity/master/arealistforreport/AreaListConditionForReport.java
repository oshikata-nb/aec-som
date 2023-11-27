package com.asahikaseieng.dao.nonentity.master.arealistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 地区検索条件
 * @author a1020630
 */
public class AreaListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public AreaListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhAreaCd; /* 地区コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhAreaCdを取得します。
	 * @return srhAreaCd
	 */
	public String getSrhAreaCd() {
		return srhAreaCd;
	}

	/**
	 * srhAreaCdを設定します。
	 * @param srhAreaCd srhAreaCd
	 */
	public void setSrhAreaCd(final String srhAreaCd) {
		this.srhAreaCd = AecTextUtils.likeFilter(srhAreaCd);
	}
}
