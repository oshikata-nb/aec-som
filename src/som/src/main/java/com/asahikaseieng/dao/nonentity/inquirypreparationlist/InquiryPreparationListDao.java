/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirypreparationlist;

import java.util.List;

/**
 * InquiryPreparationListDaoクラス
 * @author t0011036
 */
public interface InquiryPreparationListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "countDate, countDivision";

	/**
	 * Listメソッド
	 * 
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @return List<InquiryPreparationList>
	 */
	List<InquiryPreparationList> getList(final java.sql.Timestamp countDate,
			final String countDivision);
}
