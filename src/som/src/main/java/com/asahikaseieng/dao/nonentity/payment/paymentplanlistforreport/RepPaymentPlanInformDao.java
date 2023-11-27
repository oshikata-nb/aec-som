/*
 * Created on 2009/12/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepPaymentPlanInformDaoクラス
 * @author kanri-user
 */
public interface RepPaymentPlanInformDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.RepPaymentPlanInform.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "paymentNo";

	/**
	 * RepPaymentPlanInformメソッド
	 * 
	 * @param paymentno paymentno
	 * @return RepPaymentPlanInform
	 */
	List<RepPaymentPlanInform> getListForReport(
			final ArrayList<String> paymentno);
}
