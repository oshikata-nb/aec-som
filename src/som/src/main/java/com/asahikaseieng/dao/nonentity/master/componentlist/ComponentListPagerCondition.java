/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ComponentListPagerConditionクラス.
 * @author jbd
 */
public class ComponentListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentListPagerCondition() {
	}

	private String srhComponentCd;

	/**
	 * srhComponentCdを取得します。
	 * @return srhComponentCd
	 */
	public String getSrhComponentCd() {
		return srhComponentCd;
	}

	/**
	 * srhComponentCdを設定します。
	 * @param srhComponentCd srhComponentCd
	 */
	public void setSrhComponentCd(final String srhComponentCd) {
		this.srhComponentCd = AecTextUtils.likeFilter(srhComponentCd);
	}
}
