/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedelete;

/**
 * ProductAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public interface ProductAttributeQueueDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.productattributequeuedelete.
		ProductAttributeQueueDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * ProductAttributeQueueDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
