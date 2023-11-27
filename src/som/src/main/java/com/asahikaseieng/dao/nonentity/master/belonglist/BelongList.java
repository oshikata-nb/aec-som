/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * BelongListクラス.
 * @author t0011036
 */
public class BelongList extends BelongListBase {

	private static final long serialVersionUID = 1L;

	private String shortOrganizationName;

	private String shortTantoNm;

	private String shortPostName;

	/**
	 * コンストラクタ.
	 */
	public BelongList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用organizationName取得.
	 * @return organizationName
	 */
	public String getDispOrganizationName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getOrganizationName(),
			getShortOrganizationName());
	}

	/**
	 * 表示用tantoNm取得.
	 * @return tantoNm
	 */
	public String getDispTantoNm() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getTantoNm(), getShortTantoNm());
	}

	/**
	 * 表示用postName取得.
	 * @return postName
	 */
	public String getDispPostName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getPostName(), getShortPostName());
	}

	/**
	 * shortOrganizationNameを取得します。
	 * @return shortOrganizationName
	 */
	public String getShortOrganizationName() {
		return shortOrganizationName;
	}

	/**
	 * shortOrganizationNameを設定します。
	 * @param shortOrganizationName shortOrganizationName
	 */
	public void setShortOrganizationName(final String shortOrganizationName) {
		this.shortOrganizationName = shortOrganizationName;
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

	/**
	 * shortTantoNmを取得します。
	 * @return shortTantoNm
	 */
	public String getShortTantoNm() {
		return shortTantoNm;
	}

	/**
	 * shortTantoNmを設定します。
	 * @param shortTantoNm shortTantoNm
	 */
	public void setShortTantoNm(final String shortTantoNm) {
		this.shortTantoNm = shortTantoNm;
	}
}
