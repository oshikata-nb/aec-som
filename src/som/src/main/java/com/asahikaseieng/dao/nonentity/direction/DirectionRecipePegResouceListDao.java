/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 前後設備紐付けマスタ用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipePegResouceListDao {

	/** BEANアノテーション */
	Class<DirectionRecipePegResouceList> BEAN = DirectionRecipePegResouceList.class;

	//method---------------------------------------------

	/** ARGSアノテーション getResourceCd(). */
	String getResourceCd_ARGS = "compoundTankNo,holdTankNo";

	/**
	 * エンティティ取得.
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールとタンクNO
	 * @return DirectionRecipePegResouceList
	 */
	DirectionRecipePegResouceList getResourceCd(String compoundTankNo, String holdTankNo);

}
