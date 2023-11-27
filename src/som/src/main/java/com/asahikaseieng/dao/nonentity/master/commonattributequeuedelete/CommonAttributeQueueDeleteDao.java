/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commonattributequeuedelete;

/**
 * CommonAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public interface CommonAttributeQueueDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.commonattributequeuedelete.CommonAttributeQueueDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * CommonAttributeQueueDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
