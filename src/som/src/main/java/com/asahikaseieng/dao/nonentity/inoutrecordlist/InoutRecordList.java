/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * InoutRecordListクラス.
 * @author t0011036
 */
public class InoutRecordList extends InoutRecordListBase {

	private static final long serialVersionUID = 1L;

	private String strInoutDate;

	private String strInoutQty;

	private String link;

	/**
	 * コンストラクタ.
	 */
	public InoutRecordList() {
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
	 * 表示用locationName取得.
	 * @return locationName
	 */
	public String getDispLocationName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getLocationName(),
			getShortLocationName());
	}

	/**
	 * strInoutDateを取得します。
	 * @return strInoutDate
	 */
	public String getStrInoutDate() {
		return strInoutDate;
	}

	/**
	 * strInoutDateを設定します。
	 * @param strInoutDate strInoutDate
	 */
	public void setStrInoutDate(final String strInoutDate) {
		this.strInoutDate = strInoutDate;
	}

	/**
	 * strInoutQtyを取得します。
	 * @return strInoutQty
	 */
	public String getStrInoutQty() {
		return strInoutQty;
	}

	/**
	 * strInoutQtyを設定します。
	 * @param strInoutQty strInoutQty
	 */
	public void setStrInoutQty(final String strInoutQty) {
		this.strInoutQty = strInoutQty;
	}

	/**
	 * linkを取得します。
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * linkを設定します。
	 * @param link link
	 */
	public void setLink(final String link) {
		this.link = link;
	}
}
