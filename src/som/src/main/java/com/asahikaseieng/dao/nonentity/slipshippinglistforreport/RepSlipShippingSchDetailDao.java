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
 * RepSlipShippingSchDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingSchDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingSchDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingSchDetailメソッド
	 * 
	 * @param shippingno shippingno
	 * @return RepSlipShippingSchDetail
	 */
	List<RepSlipShippingSchDetail> getListForReport(
			final ArrayList<String> shippingno);
}
