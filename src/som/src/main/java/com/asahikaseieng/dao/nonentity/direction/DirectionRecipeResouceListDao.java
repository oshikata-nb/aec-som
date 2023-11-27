/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;


/**
 * 設備用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionRecipeResouceListDao {

	/** BEANアノテーション */
	Class<DirectionRecipeResouceList> BEAN = DirectionRecipeResouceList.class;

	// method-----------------------------------------------------------------

	/** ARGSアノテーション getResourceCd(). */
	String getResourceCd_ARGS = "productionLine,dissolutionTankNo";

	/**
	 * エンティティ取得.
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @return List<DirectionRecipeResouceList>
	 */
	DirectionRecipeResouceList getResourceCd(String productionLine, String dissolutionTankNo);

}
