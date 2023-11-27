/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedetail;

/**
 * ProductAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public interface ProductAttributeQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.productattributequeuedetail.
		ProductAttributeQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version";

	/**
	 * ProductAttributeQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ProductAttributeQueueDetail
	 */
	ProductAttributeQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
