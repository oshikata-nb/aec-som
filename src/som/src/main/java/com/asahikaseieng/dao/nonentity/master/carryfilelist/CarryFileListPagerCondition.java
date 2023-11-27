/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfilelist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CarryListPagerConditionクラス.
 * @author kiguchi
 */
public class CarryFileListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileListPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 運送会社コード */
	private String srhCarryCd;
	
	/** マスタ設定有無 */
	private String srhExistsSetting;


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
	

	/**
	 * srhExistsSettingを取得します。
	 * @return srhExistsSetting
	 */
	public String getSrhExistsSetting() {
		return srhExistsSetting;
	}

	/**
	 * srhExistsSettingを設定します。
	 * @param srhExistsSetting srhExistsSetting
	 */
	public void setSrhExistsSetting(String srhExistsSetting) {
		this.srhExistsSetting = srhExistsSetting;
	}	
}
