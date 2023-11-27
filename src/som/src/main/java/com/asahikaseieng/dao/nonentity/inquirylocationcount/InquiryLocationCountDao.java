/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirylocationcount;

/**
 * InquiryLocationCountDaoクラス
 * @author t0011036
 */
public interface InquiryLocationCountDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquirylocationcount.InquiryLocationCount.class;

	/** ARGSアノテーション getLocationCount */
	String getLocationCount_ARGS = "countDivision";

	/**
	 * InquiryLocationCountメソッド
	 * 
	 * @param countDivision countDivision
	 * @return InquiryLocationCount
	 */
	InquiryLocationCount getLocationCount(final String countDivision);
}
