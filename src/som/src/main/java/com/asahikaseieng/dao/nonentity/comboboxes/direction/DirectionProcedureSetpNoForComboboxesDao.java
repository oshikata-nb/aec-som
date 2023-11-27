/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.direction;

import java.util.List;

/**
 * 製造指図－工程順序コンボボックス用Daoインターフェース
 *
 * @author tosco
 */
public interface DirectionProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<DirectionProcedureSetpNoForComboboxes> BEAN = DirectionProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByDirectionNo. */
	String findByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedure>
	 */
	List<DirectionProcedureSetpNoForComboboxes> findByDirectionNo(String directionNo);
}
