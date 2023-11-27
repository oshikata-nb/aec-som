/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.util.List;

/**
 * 製造指図一覧－指図書発行-計装インターフェイス用.
 *
 * @author tosco
 */
public interface DirectionProcedureFormulaListDao {

	/** BEANアノテーション */
	Class<DirectionProcedureFormulaList> BEAN = DirectionProcedureFormulaList.class;

	// インスタンスメソッド
	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo";
	/**
	 * 指図番号に一致する、プロシージャ・フォーミュラを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedureFormulaList>
	 */
	List<DirectionProcedureFormulaList> getList(int directionDivision, String directionNo);
}
