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
 * RepPaymentHeaderDaoクラス
 * @author kanri-user
 */
public interface RepPaymentHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.RepPaymentHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "slipNo";

	/**
	 * RepPaymentHeaderメソッド
	 * 
	 * @param slipNo slipNo
	 * @return RepPaymentHeader
	 */
	List<RepPaymentHeader> getListForReport(final ArrayList<String> slipNo);
}
