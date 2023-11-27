/*
 * Created on 2009/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingSchHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingSchHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingSchHeaderメソッド
	 * 
	 * @param shippingno shippingno
	 * @return RepSlipShippingSchHeader
	 */
	List<RepSlipShippingSchHeader> getListForReport(
			final ArrayList<String> shippingno);
}
