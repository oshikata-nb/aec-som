/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.rdirection;

import java.util.List;

/**
 * 製造実績－工程順序コンボボックス用Daoインターフェース
 *
 * @author tosco
 */
public interface RdirectionProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<RdirectionProcedureSetpNoForComboboxes> BEAN = RdirectionProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByDirectionNo. */
	String findByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedure>
	 */
	List<RdirectionProcedureSetpNoForComboboxes> findByDirectionNo(String directionNo);
}
