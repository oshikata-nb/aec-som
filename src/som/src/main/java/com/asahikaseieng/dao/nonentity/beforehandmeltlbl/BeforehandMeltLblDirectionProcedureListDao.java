/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.beforehandmeltlbl;

import java.util.List;

/**
 * 製造指図プロシージャ 用Daoインターフェース.
 *
 * @author tosco
 */
public interface BeforehandMeltLblDirectionProcedureListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblDirectionProcedureList.class;

	/** ARGSアノテーション getDirectionProcedureList */
	String getDirectionProcedureList_ARGS = "directionDivision,directionNo";

	/**
	 * 予備溶解ラベル発行対象データ取得(DIRECTION_PROCEDURE)
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<BeforehandMeltLblDirectionProcedureList> 予備溶解ラベル発行対象
	 */
	List<BeforehandMeltLblDirectionProcedureList> getDirectionProcedureList(
				final String directionDivision, final String directionNo);
}
