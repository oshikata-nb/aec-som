/*
 * Created on 2009/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.artrans;

import java.util.List;

/**
 * ArTransJournalListDaoクラス
 * @author t0011036
 */
public interface ArTransJournalListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.artrans.ArTransJournalList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "denYmd";

	/**
	 * Listメソッド
	 * 
	 * @param denYmd denYmd
	 * @return List<ArTransJournalList>
	 */
	List<ArTransJournalList> getList(final String denYmd);
}
