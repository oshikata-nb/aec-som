/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装指図－工程順序コンボボックス用Daoインターフェース
 *
 * @author tosco
 */
public interface PkgDirectionProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<PkgDirectionProcedureSetpNoForComboboxes> BEAN = PkgDirectionProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByDirectionNo. */
	String findByDirectionNo_ARGS = "directionDivision, directionNo";
	/**
	 * 指図番号で検索
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedure>
	 */
	List<PkgDirectionProcedureSetpNoForComboboxes> findByDirectionNo(
		BigDecimal directionDivision, String directionNo);
}
