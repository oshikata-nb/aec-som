package com.asahikaseieng.dao.nonentity.master.nameslist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 名称検索条件
 * @author a1020630
 */
public class NamesListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public NamesListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhNameDivision; /* 名称区分 */

	private String srhNameCd; /* 名称コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhNameCdを取得します。
	 * @return srhNameCd
	 */
	public String getSrhNameCd() {
		return srhNameCd;
	}

	/**
	 * srhNameCdを設定します。
	 * @param srhNameCd srhNameCd
	 */
	public void setSrhNameCd(final String srhNameCd) {
		this.srhNameCd = AecTextUtils.likeFilter(srhNameCd);
	}

	/**
	 * srhNameDivisionを取得します。
	 * @return srhNameDivision
	 */
	public String getSrhNameDivision() {
		return srhNameDivision;
	}

	/**
	 * srhNameDivisionを設定します。
	 * @param srhNameDivision srhNameDivision
	 */
	public void setSrhNameDivision(final String srhNameDivision) {
		this.srhNameDivision = srhNameDivision;
	}
}
