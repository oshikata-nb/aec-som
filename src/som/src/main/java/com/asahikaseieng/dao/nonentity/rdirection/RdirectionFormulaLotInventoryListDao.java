/*
 * Created on 2011/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import java.util.List;

/**
 * RdirectionFormulaLotInventoryListDaoクラス
 * @author t1344224
 */
public interface RdirectionFormulaLotInventoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryList.class;

	/** ARGSアノテーション getLotList */
	String getLotList_ARGS = "lotNo";

	/**
	 * Listメソッド
	 * 
	 * @param lotno lotno
	 * @return RdirectionFormulaLotInventoryList List
	 */
	List<RdirectionFormulaLotInventoryList> getLotList(final String lotno);
}
