/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * 品目マスタキューDaoインターフェース.
 *
 * @author tosco
 */
public interface MgrecipeItemQueueDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeItemQueueDetail> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail.class;


	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "itemCd";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @return Mgrecipe
	 */
	MgrecipeItemQueueDetail getEntity(String itemCd);


}
