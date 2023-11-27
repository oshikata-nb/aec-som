/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 役職検索条件
 * @author t0011036
 */
public class PostListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PostListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhPostId; /* 役職コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhPostIdを取得します。
	 * @return srhPostId
	 */
	public String getSrhPostId() {
		return srhPostId;
	}

	/**
	 * srhPostIdを設定します。
	 * @param srhPostId srhPostId
	 */
	public void setSrhPostId(final String srhPostId) {
		this.srhPostId = AecTextUtils.likeFilter(srhPostId);
	}
}
