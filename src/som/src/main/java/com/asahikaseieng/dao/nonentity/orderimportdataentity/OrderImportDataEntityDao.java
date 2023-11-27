/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdataentity;

import java.math.BigDecimal;

/**
 * OrderImportDetailEntityDaoクラス
 * @author 
 */
public interface OrderImportDataEntityDao {

	/** BEANアノテーション */
	Class<OrderImportDataEntity> BEAN = com.asahikaseieng.dao.nonentity.orderimportdataentity.OrderImportDataEntity.class;

	/** ARGSアノテーション getImportDataEntity */
	String getImportDataEntity_ARGS = "frstOrderNo";

	/**
	 * getListメソッド
	 * 
	 * @param frstOrderNo String
	 * @return OrderImportDetailEntity
	 */
	OrderImportDataEntity getImportDataEntity( String frstOrderNo );
	
}
