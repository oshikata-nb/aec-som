/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * SalestermsListクラス.
 * @author t0011036
 */
public class SalesTermsList extends SalesTermsListBase {

	private static final long serialVersionUID = 1L;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsList() {
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
