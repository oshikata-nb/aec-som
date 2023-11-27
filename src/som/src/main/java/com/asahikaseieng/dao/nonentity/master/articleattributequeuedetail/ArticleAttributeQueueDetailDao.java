/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail;

/**
 * ArticleAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public interface ArticleAttributeQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail.
		ArticleAttributeQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version";

	/**
	 * ArticleAttributeQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ArticleAttributeQueueDetail
	 */
	ArticleAttributeQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
