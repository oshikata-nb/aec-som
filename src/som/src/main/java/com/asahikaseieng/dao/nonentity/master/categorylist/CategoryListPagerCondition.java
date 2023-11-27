/*
 * Created on 2008/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorylist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * CategoryListPagerConditionクラス.
 * @author tosco
 */
public class CategoryListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CategoryListPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 名称区分 */
	private String categoryDivision;

	/**
	 * categoryDivisionを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * categoryDivisionを設定します。
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = AecTextUtils.likeFilter(categoryDivision);
	}
}
