/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistdetail;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * BalanceListDetailクラス.
 * @author t0011036
 */
public class BalanceListDetail extends BalanceListDetailBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BalanceListDetail() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用venderName1取得.
	 * @return venderName1
	 */
	public String getDispVenderName1() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName1(), getShortVenderName1());
	}

	/**
	 * 表示用venderName2取得.
	 * @return venderName2
	 */
	public String getDispVenderName2() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName2(), getShortVenderName2());
	}

	/**
	 * 表示用venderName3取得.
	 * @return venderName3
	 */
	public String getDispVenderName3() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName3(), getShortVenderName3());
	}

	/**
	 * 表示用venderName4取得.
	 * @return venderName4
	 */
	public String getDispVenderName4() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName4(), getShortVenderName4());
	}

	/**
	 * 表示用venderName5取得.
	 * @return venderName5
	 */
	public String getDispVenderName5() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getVenderName5(), getShortVenderName5());
	}
}
