/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * BumonListクラス.
 * @author t0011036
 */
public class BumonList extends BumonListBase {

	private static final long serialVersionUID = 1L;

	private String shortSectionName;

	private String shortSectionShortedName;

	/**
	 * コンストラクタ.
	 */
	public BumonList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用sectionName取得.
	 * @return sectionName
	 */
	public String getDispSectionName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getSectionName(), getShortSectionName());
	}

	/**
	 * 表示用sectionShortedName取得.
	 * @return sectionShortedName
	 */
	public String getDispSectionShortedName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getSectionShortedName(),
			getShortSectionShortedName());
	}

	/**
	 * shortSectionNameを取得します。
	 * @return shortSectionName
	 */
	public String getShortSectionName() {
		return shortSectionName;
	}

	/**
	 * shortSectionNameを設定します。
	 * @param shortSectionName shortSectionName
	 */
	public void setShortSectionName(final String shortSectionName) {
		this.shortSectionName = shortSectionName;
	}

	/**
	 * shortSectionShortedNameを取得します。
	 * @return shortSectionShortedName
	 */
	public String getShortSectionShortedName() {
		return shortSectionShortedName;
	}

	/**
	 * shortSectionShortedNameを設定します。
	 * @param shortSectionShortedName shortSectionShortedName
	 */
	public void setShortSectionShortedName(final String shortSectionShortedName) {
		this.shortSectionShortedName = shortSectionShortedName;
	}
}
