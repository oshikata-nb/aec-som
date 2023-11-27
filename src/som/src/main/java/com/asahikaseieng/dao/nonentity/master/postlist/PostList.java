/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * PostListクラス.
 * @author t0011036
 */
public class PostList extends PostListBase {

	private static final long serialVersionUID = 1L;

	private String shortPostName;

	/**
	 * コンストラクタ.
	 */
	public PostList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用postName取得.
	 * @return postName
	 */
	public String getDispPostName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getPostName(), getShortPostName());
	}

	/**
	 * shortPostNameを取得します。
	 * @return shortPostName
	 */
	public String getShortPostName() {
		return shortPostName;
	}

	/**
	 * shortPostNameを設定します。
	 * @param shortPostName shortPostName
	 */
	public void setShortPostName(final String shortPostName) {
		this.shortPostName = shortPostName;
	}
}
