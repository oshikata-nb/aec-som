/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

/**
 * 品目マスタキューDaoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeItemQueueDetailDao {

	/** BEANアノテーション */
	Class<GrecipeItemQueueDetail> BEAN = GrecipeItemQueueDetail.class;


	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "itemCd";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @return Mgrecipe
	 */
	GrecipeItemQueueDetail getEntity(String itemCd);


}
