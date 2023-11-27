/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateduplicatelist;

import java.util.List;

/**
 * EstimateDuplicateDaoクラス
 * @author t0011036
 */
public interface EstimateDuplicateListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimateduplicatelist.EstimateDuplicateList.class;

	/** ARGSアノテーション getDuplicateList */
	String getDuplicateList_ARGS = "estimateNo,balanceCd, itemCd, strEstimateValidDateFrom, strEstimateValidDateTo";

	/**
	 * EstimateDuplicateメソッド
	 * 
	 * @param estimateNo estimateNo
	 * @param balanceCd balanceCd
	 * @param itemCd itemCd
	 * @param strEstimateValidDateFrom strEstimateValidDateFrom
	 * @param strEstimateValidDateTo strEstimateValidDateTo
	 * @return List<EstimateDuplicateList>
	 */
	List<EstimateDuplicateList> getDuplicateList(final String estimateNo,
			final String balanceCd, final String itemCd,
			final String strEstimateValidDateFrom,
			final String strEstimateValidDateTo);
}
