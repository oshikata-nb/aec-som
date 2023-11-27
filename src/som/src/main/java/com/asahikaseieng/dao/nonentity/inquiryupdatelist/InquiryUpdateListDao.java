/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryupdatelist;

import java.util.List;

/**
 * InquiryUpdateListDaoクラス
 * @author t0011036
 */
public interface InquiryUpdateListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquiryupdatelist.InquiryUpdateList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "countDate, countDivision, countLocation, itemCd, lotNo";

	/**
	 * Listメソッド
	 * 
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @param countLocation countLocation
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return List<InquiryUpdateList>
	 */
	List<InquiryUpdateList> getList(final java.sql.Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo);
}
