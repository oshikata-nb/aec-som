/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装実績－工程順序コンボボックス用Daoインターフェース
 *
 * @author tosco
 */
public interface PkgRdirectionProcedureSetpNoForComboboxesDao {

	/** BEANアノテーション */
	Class<PkgRdirectionProcedureSetpNoForComboboxes> BEAN = PkgRdirectionProcedureSetpNoForComboboxes.class;

	/** ARGSアノテーション findByDirectionNo. */
	String findByDirectionNo_ARGS = "directionDivision, directionNo";
	/**
	 * 指図番号で検索
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedure>
	 */
	List<PkgRdirectionProcedureSetpNoForComboboxes> findByDirectionNo(
		BigDecimal directionDivision, String directionNo);
}
