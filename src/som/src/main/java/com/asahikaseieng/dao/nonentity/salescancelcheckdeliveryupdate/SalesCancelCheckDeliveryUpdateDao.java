/*
 * Created on 2009/12/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate;

/**
 * SalesCancelCheckDeliveryUpdateDaoクラス
 * @author kanri-user
 */
public interface SalesCancelCheckDeliveryUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate.SalesCancelCheckDeliveryUpdate.class;

	/** ARGSアノテーション getMaxDeliveryUpdateDate */
	String getMaxDeliveryUpdateDate_ARGS = "invoiceCd, salesDate";

	/**
	 * SalesCancelCheckDeliveryUpdateメソッド
	 * 
	 * @param invoiceCd invoiceCd
	 * @param salesDate salesDate
	 * @return SalesCancelCheckDeliveryUpdate
	 */
	SalesCancelCheckDeliveryUpdate getMaxDeliveryUpdateDate(
			final String invoiceCd, final String salesDate);
}
