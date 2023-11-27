package com.asahikaseieng.dao.nonentity.master.reasonlist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 理由検索条件
 * @author a1020630
 */
public class ReasonListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ReasonListPagerCondition() {
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
