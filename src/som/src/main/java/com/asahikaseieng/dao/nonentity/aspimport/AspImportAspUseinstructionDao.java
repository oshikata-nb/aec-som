/*
 * Created on Tue Apr 21 10:54:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportAspUseinstructionDaoインターフェース.
 * @author
 */
public interface AspImportAspUseinstructionDao {

	/** BEANアノテーション. */
	Class BEAN = AspImportAspUseinstruction.class;

	//
	// インスタンスメソッド
	//


	/** ARGSアノテーション getEntityByOrderCd(). */
	String getEntityByOrderCd_ARGS = "orderCd";

	/**
	 * エンティティ取得.
	 * @param orderCd orderCd
	 * @return List<AspImportAspUseinstruction>
	 */
	List<AspImportAspUseinstruction> getEntityByOrderCd(final String orderCd);
}
