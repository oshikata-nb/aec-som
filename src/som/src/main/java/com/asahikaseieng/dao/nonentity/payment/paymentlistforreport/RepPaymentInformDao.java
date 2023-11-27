/*
 * Created on 2009/08/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepPaymentInformDaoクラス
 * @author kanri-user
 */
public interface RepPaymentInformDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentInform.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "slipNo";

	/**
	 * RepPaymentInformメソッド
	 * 
	 * @param slipno slipno
	 * @return RepPaymentInform
	 */
	List<RepPaymentInform> getListForReport(final ArrayList<String> slipno);
}
