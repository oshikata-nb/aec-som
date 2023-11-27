/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueueheader;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ItemQueueHeaderクラス.
 * @author t0011036
 */
public class ItemQueueHeader extends ItemQueueHeaderBase {

	private static final long serialVersionUID = 1L;

	private String strHeadActiveDate;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueHeader() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用headDispItemName取得.
	 * @return headItemName
	 */
	public String getHeadDispItemName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getHeadItemName(), getShortItemName());
	}

	/**
	 * shortItemNameを取得します。
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return shortItemName;
	}

	/**
	 * shortItemNameを設定します。
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
	}

	/**
	 * strHeadActiveDateを取得します。
	 * @return strHeadActiveDate
	 */
	public String getStrHeadActiveDate() {
		return strHeadActiveDate;
	}

	/**
	 * strHeadActiveDateを設定します。
	 * @param strHeadActiveDate strHeadActiveDate
	 */
	public void setStrHeadActiveDate(final String strHeadActiveDate) {
		this.strHeadActiveDate = strHeadActiveDate;
	}
}
