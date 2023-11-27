/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderfilelayoutchange;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderImportHeadDaoインターフェース.
 * @author 
 */
public interface OrderFileLayoutChangeDao {

	/** BEANアノテーション. */
	Class<OrderFileLayoutChange> BEAN = OrderFileLayoutChange.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderFileLayoutChange
	 * @return Insert件数
	 */
	int insert(OrderFileLayoutChange bean);

	/**
	 * Update.
	 * @param bean OrderFileLayoutChange
	 * @return Update件数
	 */
	int update(OrderFileLayoutChange bean);

	/**
	 * Delete.
	 * @param bean OrderFileLayoutChange
	 * @return Delete件数
	 */
	int delete(OrderFileLayoutChange bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "VENDER_GROUP_CD, CELL_COLUMN_NUMBER";

	/**
	 * エンティティ取得.
	 * @param venderGroupCd String
	 * @param cellColumnNumber BigDecimal
	 * @return OrderFileLayoutChange
	 */
	OrderFileLayoutChange getEntity(String venderGroupCd, BigDecimal cellColumnNumber);

}
