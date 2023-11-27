/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ComponentListPagerConditionクラス.
 * @author jbd
 */
public class ComponentListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentListConditionForReport() {
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
