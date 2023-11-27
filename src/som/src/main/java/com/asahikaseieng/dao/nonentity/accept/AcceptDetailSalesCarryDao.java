/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

/**
 * AcceptDetailSalesCarryDaoクラス
 * @author kanri-user
 */
public interface AcceptDetailSalesCarryDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptDetailSalesCarry.class;

	/** ARGSアノテーション getSalesData */
	String getSalesData_ARGS = "companyCd, orderNo, rowNo, itemCd";

	/**
	 * AcceptDetailSalesCarryメソッド
	 * 
	 * @param companyCd companyCd
	 * @param orderNo orderNo
	 * @param rowNo rowNo
	 * @param itemCd itemCd
	 * @return AcceptDetailSalesCarry
	 */
	AcceptDetailSalesCarry getSalesData(final String companyCd,
			final String orderNo, final String rowNo, final String itemCd);
}
