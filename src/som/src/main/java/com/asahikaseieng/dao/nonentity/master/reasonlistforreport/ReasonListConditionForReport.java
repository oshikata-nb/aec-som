package com.asahikaseieng.dao.nonentity.master.reasonlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 理由検索条件
 * @author a1020630
 */
public class ReasonListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ReasonListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhRyCd; /* 理由コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhRyCdを取得します。
	 * @return srhRyCd
	 */
	public String getSrhRyCd() {
		return srhRyCd;
	}

	/**
	 * srhRyCdを設定します。
	 * @param srhRyCd srhRyCd
	 */
	public void setSrhRyCd(final String srhRyCd) {
		this.srhRyCd = AecTextUtils.likeFilter(srhRyCd);
	}
}
