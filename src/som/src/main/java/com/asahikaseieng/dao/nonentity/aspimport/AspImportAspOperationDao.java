/*
 * Created on Tue Feb 17 10:00:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.util.List;

/**
 * AspImportAspOperationDaoインターフェース.
 * @author 
 */
public interface AspImportAspOperationDao {

	/** BEANアノテーション. */
	Class BEAN = AspImportAspOperation.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntityByOrderCd(). */
	String getEntity_ARGS = "orderCd";

	/**
	 * エンティティ取得.
	 * @param orderCd orderCd
	 * @return List<AspImportAspOperation>
	 */
	List<AspImportAspOperation> getEntityByOrderCd(String orderCd);

	/** ARGSアノテーション getEntityByOrderCd(). */
	String getEntityByMunufuctOrderCd = "orderCd";

	/**
	 * エンティティ取得.
	 * @param orderCd orderCd
	 * @return List<AspImportAspOperation>
	 */
	List<AspImportAspOperation> getEntityByMunufuctOrderCd(String orderCd);

}
