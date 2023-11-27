/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetailentity;

import java.math.BigDecimal;

/**
 * OrderImportDetailEntityDaoクラス
 * @author 
 */
public interface OrderImportDetailEntityDao {

	/** BEANアノテーション */
	Class<OrderImportDetailHeaderEntity> BEAN = com.asahikaseieng.dao.nonentity.orderimportdetailentity.OrderImportDetailHeaderEntity.class;

	/** ARGSアノテーション getEntity */
	String getHeaderEntity_ARGS = "frstOrderNo";

	/**
	 * getListメソッド
	 * 
	 * @param pkNo String
	 * @param pkRow BigDecimal
	 * @return OrderImportDetailEntity
	 */
	OrderImportDetailHeaderEntity getHeaderEntity( String frstOrderNo );
	
	
	/** ARGSアノテーション getMaxPkRowByPkNo */
	String getMaxPkRowByPkNo_ARGS = "frstOrderNo";

	/**
	 * getMaxFrstOrderRowメソッド
	 * 
	 * @param pkNo String
	 * @return BigDecimal
	 */
	BigDecimal getMaxFrstOrderRow(String frstOrderNo);
}
