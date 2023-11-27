/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CarryListクラス.
 * @author t0011036
 */
public class CarryList extends CarryListBase {

	private static final long serialVersionUID = 1L;

	private String shortCarryName1;

	private String shortCarryName2;

	private String shortAbbreviation;

	/**
	 * コンストラクタ.
	 */
	public CarryList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用carryName1取得.
	 * @return carryName1
	 */
	public String getDispCarryName1() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getCarryName1(), getShortCarryName1());
	}

	/**
	 * 表示用carryName2取得.
	 * @return carryName2
	 */
	public String getDispCarryName2() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getCarryName2(), getShortCarryName2());
	}

	/**
	 * 表示用abbreviation取得.
	 * @return abbreviation
	 */
	public String getDispAbbreviation() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getAbbreviation(),
			getShortAbbreviation());
	}

	/**
	 * shortAbbreviationを取得します。
	 * @return shortAbbreviation
	 */
	public String getShortAbbreviation() {
		return shortAbbreviation;
	}

	/**
	 * shortAbbreviationを設定します。
	 * @param shortAbbreviation shortAbbreviation
	 */
	public void setShortAbbreviation(final String shortAbbreviation) {
		this.shortAbbreviation = shortAbbreviation;
	}

	/**
	 * shortCarryName1を取得します。
	 * @return shortCarryName1
	 */
	public String getShortCarryName1() {
		return shortCarryName1;
	}

	/**
	 * shortCarryName1を設定します。
	 * @param shortCarryName1 shortCarryName1
	 */
	public void setShortCarryName1(final String shortCarryName1) {
		this.shortCarryName1 = shortCarryName1;
	}

	/**
	 * shortCarryName2を取得します。
	 * @return shortCarryName2
	 */
	public String getShortCarryName2() {
		return shortCarryName2;
	}

	/**
	 * shortCarryName2を設定します。
	 * @param shortCarryName2 shortCarryName2
	 */
	public void setShortCarryName2(final String shortCarryName2) {
		this.shortCarryName2 = shortCarryName2;
	}
}
