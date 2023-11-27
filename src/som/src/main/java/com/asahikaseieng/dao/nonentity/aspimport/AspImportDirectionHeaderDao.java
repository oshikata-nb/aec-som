/*
 * Created on Thu Jan 22 11:01:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportDirectionHeaderDaoインターフェース.
 * @author t0011036
 */
public interface AspImportDirectionHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = AspImportDirectionHeader.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getDirectionNoByOrderCd */
	String getDirectionNoByOrderCd_ARGS = "orderCd";

	/**
	 * エンティティ取得.
	 * @param orderCd orderCd
	 * @return List<DirectionHeader>
	 */
	List<AspImportDirectionHeader> getDirectionNoByOrderCd(
			final String[] orderCd);
}
