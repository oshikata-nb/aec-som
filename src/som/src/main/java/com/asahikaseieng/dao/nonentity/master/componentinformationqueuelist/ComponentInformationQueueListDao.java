/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist;

import java.math.BigDecimal;
import java.util.List;

/**
 * ComponentInformationQueueListDaoクラス
 * @author t0011036
 */
public interface ComponentInformationQueueListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.
		ComponentInformationQueueList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "itemCd,version";

	/**
	 * Listメソッド
	 * 
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return List<ComponentInformationQueueList>
	 */
	List<ComponentInformationQueueList> getList(final String itemCd,
			final BigDecimal version);
}
