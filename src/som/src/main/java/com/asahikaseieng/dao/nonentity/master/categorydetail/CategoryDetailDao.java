/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorydetail;

/**
 * CategoryDetailDaoクラス
 * @author t0011036
 */
public interface CategoryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.categorydetail.CategoryDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "categoryCd, categoryDivision";

	/**
	 * CategoryDetailメソッド
	 * 
	 * @param categoryCd categoryCd
	 * @param categoryDivision categoryDivision
	 * @return CategoryDetail
	 */
	CategoryDetail getEntity(final String categoryCd,
			final String categoryDivision);
}
