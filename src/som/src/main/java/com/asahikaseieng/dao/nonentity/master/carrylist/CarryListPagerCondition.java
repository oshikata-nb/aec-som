/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CarryListPagerConditionクラス.
 * @author tosco
 */
public class CarryListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryListPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 運送会社コード */
	private String srhCarryCd;
	

	/**
	 * srhCarryCdを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * srhCarryCdを設定します。
	 * @param srhCarryCd srhCarryCd
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = AecTextUtils.likeFilter(srhCarryCd);
	}

}
