package com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 設備グループ検索条件
 * @author a1020630
 */
public class RecipeResouceGroupListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceGroupListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhResouceGroupCd; /* 設備グループコード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhResouceGroupCdを取得します。
	 * @return srhResouceGroupCd
	 */
	public String getSrhResouceGroupCd() {
		return srhResouceGroupCd;
	}

	/**
	 * srhResouceGroupCdを設定します。
	 * @param srhResouceGroupCd srhResouceGroupCd
	 */
	public void setSrhResouceGroupCd(final String srhResouceGroupCd) {
		this.srhResouceGroupCd = AecTextUtils.likeFilter(srhResouceGroupCd);
	}
}
