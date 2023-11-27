/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;


/**
 * DirectionFormulaTempDaoインターフェース.
 * @author 
 */
public interface DirectionFormulaTempDao {

	/** BEANアノテーション. */
	Class BEAN = DirectionFormulaTemp.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionFormula
	 * @return Insert件数
	 */
	int insert(DirectionFormulaTemp bean);

	/**
	 * Update.
	 * @param bean DirectionFormula
	 * @return Update件数
	 */
	int update(DirectionFormulaTemp bean);

	/**
	 * Delete.
	 * @param bean DirectionFormula
	 * @return Delete件数
	 */
	int delete(DirectionFormulaTemp bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TEMP_NO,ROW_NUMBER";

	/**
	 * エンティティ取得.
	 * @param tempNo tempNo
	 * @param rowNumber rowNumber
	 * @return DirectionFormulaTemp
	 */
	DirectionFormulaTemp getEntity(final String tempNo, final java.math.BigDecimal rowNumber);

}
