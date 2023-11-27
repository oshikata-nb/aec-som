/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repdeliverydatecontactdetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * RepOrdDDContactDaoクラス
 * @author 
 */
public interface RepDeliveryDateContactDetailDao {

	/** BEANアノテーション */
	Class<RepDeliveryDateContactDetail> BEAN = com.asahikaseieng.dao.nonentity.repdeliverydatecontactdetail.RepDeliveryDateContactDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "pkNo";

	/**
	 * Listメソッド
	 * 
	 * @param pkNo
	 * @param pkRow
	 * @return entity
	 */
	List<RepDeliveryDateContactDetail> getEntity(String pkNo);
	
}
