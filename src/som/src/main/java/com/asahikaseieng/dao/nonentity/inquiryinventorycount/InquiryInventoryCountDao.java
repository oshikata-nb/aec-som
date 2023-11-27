/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinventorycount;

/**
 * InquiryInventoryCountDaoクラス
 * @author t0011036
 */
public interface InquiryInventoryCountDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount.class;

	/** ARGSアノテーション getInventoryCount */
	String getInventoryCount_ARGS = "countDate, countDivision, countLocation, itemCd, lotNo, countUpdateDate";

	/**
	 * InquiryInventoryCountメソッド
	 * 
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @param countLocation countLocation
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @param countUpdateDate countUpdateDate
	 * @return InquiryInventoryCount
	 */
	InquiryInventoryCount getInventoryCount(final java.sql.Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo,
			java.sql.Timestamp countUpdateDate);
}
