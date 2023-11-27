/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlist;

import java.util.List;

/**
 * InquiryInputListDaoクラス
 * @author t0011036
 */
public interface InquiryInputListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<InquiryInputList>
	 */
	List<InquiryInputList> getList(
			final InquiryInputListPagerCondition condition);
}
