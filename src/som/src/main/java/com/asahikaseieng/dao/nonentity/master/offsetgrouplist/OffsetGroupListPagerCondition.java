package com.asahikaseieng.dao.nonentity.master.offsetgrouplist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 相殺グループ検索条件
 * @author a1020630
 */
public class OffsetGroupListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OffsetGroupListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhOffsetGroupCd; /* 相殺グループコード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhOffsetGroupCdを取得します。
	 * @return srhOffsetGroupCd
	 */
	public String getSrhOffsetGroupCd() {
		return srhOffsetGroupCd;
	}

	/**
	 * srhOffsetGroupCdを設定します。
	 * @param srhOffsetGroupCd srhOffsetGroupCd
	 */
	public void setSrhOffsetGroupCd(final String srhOffsetGroupCd) {
		this.srhOffsetGroupCd = AecTextUtils.likeFilter(srhOffsetGroupCd);
	}
}
