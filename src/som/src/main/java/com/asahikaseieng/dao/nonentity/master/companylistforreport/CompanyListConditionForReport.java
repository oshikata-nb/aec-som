/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CompanyListConditionForReportクラス.
 * @author tosco
 */
public class CompanyListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CompanyListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 自社名称1 */
	private String srhHomeName1;

	/**
	 * srhHomeName1を取得します。
	 * @return srhHomeName1
	 */
	public String getSrhHomeName1() {
		return srhHomeName1;
	}

	/**
	 * srhHomeName1を設定します。
	 * @param srhHomeName1 srhHomeName1
	 */
	public void setSrhHomeName1(final String srhHomeName1) {
		this.srhHomeName1 = AecTextUtils.likeFilter(srhHomeName1);
	}
}
