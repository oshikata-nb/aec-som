/*
 * Created on 2009/07/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingPericanDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingPericanDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingPerican.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingPericanメソッド
	 * 
	 * @param shippingno shippingno
	 * @return RepSlipShippingPerican
	 */
	List<RepSlipShippingPerican> getListForReport(
			final ArrayList<String> shippingno);
}
