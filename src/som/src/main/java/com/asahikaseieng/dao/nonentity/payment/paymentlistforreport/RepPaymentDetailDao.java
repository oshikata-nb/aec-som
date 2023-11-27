/*
 * Created on 2009/07/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepPaymentDetailDaoクラス
 * @author kanri-user
 */
public interface RepPaymentDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "slipNo";

	/**
	 * RepPaymentDetailメソッド
	 * 
	 * @param slipno slipno
	 * @return RepPaymentDetail
	 */
	List<RepPaymentDetail> getListForReport(final ArrayList<String> slipno);
}
