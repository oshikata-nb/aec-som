/*
 * Created on 2007/10/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ItemQueueListクラス.
 * @author kanri-user
 */
public class ItemQueueList extends ItemQueueListBase {

	private static final long serialVersionUID = 1L;

	private String strActiveDate;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用itemName取得.
	 * @return itemName
	 */
	public String getDispItemName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getItemName(), getShortItemName());
	}

	/**
	 * strActiveDateを取得します。
	 * @return strActiveDate
	 */
	public String getStrActiveDate() {
		return strActiveDate;
	}

	/**
	 * strActiveDateを設定します。
	 * @param strActiveDate strActiveDate
	 */
	public void setStrActiveDate(final String strActiveDate) {
		this.strActiveDate = strActiveDate;
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
}
