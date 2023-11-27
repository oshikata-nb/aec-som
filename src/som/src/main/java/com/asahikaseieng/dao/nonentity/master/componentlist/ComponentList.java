/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ComponentListクラス.
 * @author t0011036
 */
public class ComponentList extends ComponentListBase {

	private static final long serialVersionUID = 1L;

	private String shortComponentName;

	/**
	 * コンストラクタ.
	 */
	public ComponentList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用componentName1取得.
	 * @return componentName
	 */
	public String getDispComponentName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getComponentName(),
			getShortComponentName());
	}

	/**
	 * shortComponentNameを取得します。
	 * @return shortComponentName
	 */
	public String getShortComponentName() {
		return shortComponentName;
	}

	/**
	 * shortComponentNameを設定します。
	 * @param shortComponentName shortComponentName
	 */
	public void setShortComponentName(final String shortComponentName) {
		this.shortComponentName = shortComponentName;
	}
}
