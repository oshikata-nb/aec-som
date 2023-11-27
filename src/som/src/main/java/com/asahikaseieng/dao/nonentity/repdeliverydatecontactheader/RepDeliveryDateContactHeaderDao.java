/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader;

import java.math.BigDecimal;

/**
 * RepOrdDDContactHeaderDaoクラス
 * @author 
 */
public interface RepDeliveryDateContactHeaderDao {

	/** BEANアノテーション */
	Class<RepDeliveryDateContactHeader> BEAN = com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader.RepDeliveryDateContactHeader.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "pkNo , orderNo";

	/**
	 * Listメソッド
	 * 
	 * @param pkNo
	 * @param pkRow
	 * @return entity
	 */
	RepDeliveryDateContactHeader getEntity(String pkNo , String orderNo);
	
}
