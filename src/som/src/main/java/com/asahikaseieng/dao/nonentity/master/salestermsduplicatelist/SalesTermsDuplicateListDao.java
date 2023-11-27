/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist;

import java.util.List;

/**
 * SalesTermsDuplicateListDaoクラス
 * @author t0011036
 */
public interface SalesTermsDuplicateListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist.SalesTermsDuplicateList.class;

	/** ARGSアノテーション getDuplicateList */
	String getDuplicateList_ARGS = "deliveryCd, balanceCd, itemCd";

	/**
	 * Listメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @param itemCd itemCd
	 * @return List<SalesTermsDuplicateList>
	 */
	List<SalesTermsDuplicateList> getDuplicateList(final String deliveryCd,
			final String balanceCd, final String itemCd);

	/** ARGSアノテーション getDuplicateAllList */
	String getDuplicateAllList_ARGS = "deliveryCd, balanceCd, itemCd";

	/**
	 * Listメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @param itemCd itemCd
	 * @return List<SalesTermsDuplicateList>
	 */
	List<SalesTermsDuplicateList> getDuplicateAllList(final String deliveryCd,
			final String balanceCd, final String itemCd);
}
