/*
 * Created on 2009/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aptrans;

import java.util.List;

/**
 * ApTransJournalListDaoクラス
 * @author t0011036
 */
public interface ApTransJournalListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.aptrans.ApTransJournalList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "denYmd";

	/**
	 * Listメソッド
	 * 
	 * @param denYmd denYmd
	 * @return List<ApTransJournalList>
	 */
	List<ApTransJournalList> getList(final String denYmd);
}
