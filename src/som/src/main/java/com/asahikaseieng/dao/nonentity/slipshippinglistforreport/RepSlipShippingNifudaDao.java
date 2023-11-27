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
 * RepSlipShippingNifudaDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingNifudaDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingNifuda.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingNifudaメソッド
	 * 
	 * @param shippingno shippingno
	 * @return RepSlipShippingNifuda
	 */
	List<RepSlipShippingNifuda> getListForReport(
			final ArrayList<String> shippingno);
}
