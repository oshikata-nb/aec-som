/*
 * Created on 2009/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepTegataDetailDaoクラス
 * @author kanri-user
 */
public interface RepTegataDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepTegataDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "";

	/**
	 * RepTegataDetailメソッド
	 * 
	 * @param slipNo slipNo
	 * @return RepTegataDetail
	 */
	List<RepTegataDetail> getListForReport(final ArrayList<String> slipNo);
}
