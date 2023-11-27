package com.asahikaseieng.dao.nonentity.master.financialclasslist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 財務分類検索条件
 * @author a1020630
 */
public class FinancialClassListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public FinancialClassListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhFinancialClassCd; /* 財務分類コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhFinancialClassCdを取得します。
	 * @return srhFinancialClassCd
	 */
	public String getSrhFinancialClassCd() {
		return srhFinancialClassCd;
	}

	/**
	 * srhFinancialClassCdを設定します。
	 * @param srhFinancialClassCd srhFinancialClassCd
	 */
	public void setSrhFinancialClassCd(final String srhFinancialClassCd) {
		this.srhFinancialClassCd = AecTextUtils.likeFilter(srhFinancialClassCd);
	}
}
