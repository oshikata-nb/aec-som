/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete;

/**
 * ArticleAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public interface ArticleAttributeQueueDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete.
		ArticleAttributeQueueDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * ArticleAttributeQueueDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
