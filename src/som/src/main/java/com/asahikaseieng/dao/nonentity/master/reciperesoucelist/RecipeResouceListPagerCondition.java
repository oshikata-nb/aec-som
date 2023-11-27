package com.asahikaseieng.dao.nonentity.master.reciperesoucelist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 設備検索条件
 * @author a1020630
 */
public class RecipeResouceListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhResouceCd; /* 設備コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhResouceCdを取得します。
	 * @return srhResouceCd
	 */
	public String getSrhResouceCd() {
		return srhResouceCd;
	}

	/**
	 * srhResouceCdを設定します。
	 * @param srhResouceCd srhResouceCd
	 */
	public void setSrhResouceCd(final String srhResouceCd) {
		this.srhResouceCd = AecTextUtils.likeFilter(srhResouceCd);
	}
}
