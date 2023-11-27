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
 * RepSlipShippingDirDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingDirDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshippinglistforreport.RepSlipShippingDirDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "shippingNo";

	/**
	 * RepSlipShippingDirDetailメソッド
	 *
     * @param shippingno	shippingno
	 * @return RepSlipShippingDirDetail
	 */
	List<RepSlipShippingDirDetail> getListForReport(
		final ArrayList<String> shippingno
	);
}
