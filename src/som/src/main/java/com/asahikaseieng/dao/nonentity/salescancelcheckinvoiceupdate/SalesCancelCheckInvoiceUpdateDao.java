/*
 * Created on 2009/12/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate;

/**
 * SalesCancelCheckInvoiceUpdateDaoクラス
 * @author kanri-user
 */
public interface SalesCancelCheckInvoiceUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.salescancelcheckinvoiceupdate.SalesCancelCheckInvoiceUpdate.class;

	/** ARGSアノテーション getMaxInvoiceUpdateDate */
	String getMaxInvoiceUpdateDate_ARGS = "invoiceCd, salesDate";

	/**
	 * SalesCancelCheckInvoiceUpdateメソッド
	 * 
	 * @param invoiceCd invoiceCd
	 * @param salesDate salesDate
	 * @return SalesCancelCheckInvoiceUpdate
	 */
	SalesCancelCheckInvoiceUpdate getMaxInvoiceUpdateDate(
			final String invoiceCd, final String salesDate);
}
