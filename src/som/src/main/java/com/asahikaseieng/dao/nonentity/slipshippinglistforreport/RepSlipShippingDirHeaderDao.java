/*
 * Created on 2009/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingDirHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingDirHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingDirHeaderメソッド
	 * 
	 * @param shippingno shippingno
	 * @return RepSlipShippingDirHeader
	 */
	List<RepSlipShippingDirHeader> getListForReport(
			final ArrayList<String> shippingno);
}
