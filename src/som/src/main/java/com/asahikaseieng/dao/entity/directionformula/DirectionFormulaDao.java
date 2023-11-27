/*
 * Created on Fri Feb 20 19:09:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionformula;

import java.util.List;

/**
 * DirectionFormulaDaoインターフェース.
 * @author kanri-user
 */
public interface DirectionFormulaDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionFormula.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionFormula
	 * @return Insert件数
	 */
	int insert(DirectionFormula bean);

	/**
	 * Update.
	 * @param bean DirectionFormula
	 * @return Update件数
	 */
	int update(DirectionFormula bean);

	/**
	 * Delete.
	 * @param bean DirectionFormula
	 * @return Delete件数
	 */
	int delete(DirectionFormula bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DIRECTION_DIVISION,DIRECTION_NO,LINE_NO,STEP_NO";

	/**
	 * エンティティ取得.
	 * @param directionDivision directionDivision
	 * @param directionNo directionNo
	 * @param lineNo lineNo
	 * @param stepNo stepNo
	 * @return DirectionFormula
	 */
	DirectionFormula getEntity(java.math.BigDecimal directionDivision,
			String directionNo, java.math.BigDecimal lineNo,
			java.math.BigDecimal stepNo);

	/** ARGSアノテーション getListForWater(). */
	String getListForWater_ARGS = "directionNo,stepNo,itemCodes";

	/**
	 * リスト取得.
	 * @param directionNo directionNo
	 * @param stepNo stepNo
	 * @param itemCodes .
	 * @return List<DirectionFormula>
	 */
	List<DirectionFormula> getListForWater(String directionNo,
			java.math.BigDecimal stepNo, String[] itemCodes);
}
