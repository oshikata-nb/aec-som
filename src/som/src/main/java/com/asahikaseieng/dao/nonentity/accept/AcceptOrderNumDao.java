/*
 * Created on 2009/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;


/**
 * AcceptOrderNumDaoクラス
 * @author kanri-user
 */
public interface AcceptOrderNumDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptOrderNum.class;

	/** ARGSアノテーション getOrderNumData */
	String getOrderNumData_ARGS = "orderNo, orderRowNo, status";

	/**
	 * AcceptOrderNumメソッド
	 *
     * @param orderno	orderno
     * @param orderrowno	orderrowno
     * @param status	status
	 * @return AcceptOrderNum
	 */
	AcceptOrderNum getOrderNumData(
		final Object orderno,
		final Object orderrowno,
		final Object status
	);
}
