/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricelist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * UnitpriceListクラス.
 * @author kanri-user
 */
public class UnitpriceList extends UnitpriceListBase {

	private static final long serialVersionUID = 1L;

	private String strVersion;

	private String strValidDate;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceList() {
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
	 * strValidDateを取得します。
	 * @return strValidDate
	 */
	public String getStrValidDate() {
		return strValidDate;
	}

	/**
	 * strValidDateを設定します。
	 * @param strValidDate strValidDate
	 */
	public void setStrValidDate(final String strValidDate) {
		this.strValidDate = strValidDate;
	}

	/**
	 * strVersionを取得します。
	 * @return strVersion
	 */
	public String getStrVersion() {
		return strVersion;
	}

	/**
	 * strVersionを設定します。
	 * @param strVersion strVersion
	 */
	public void setStrVersion(final String strVersion) {
		this.strVersion = strVersion;
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
