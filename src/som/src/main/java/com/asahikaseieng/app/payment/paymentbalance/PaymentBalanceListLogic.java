/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceList;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * ArLedgerListLogic.支払残高一覧
 * @author tosco
 */
public interface PaymentBalanceListLogic {

	/**
	 * 検索処理を行う.支払残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<PaymentBalanceList> getSearchList(
			final PaymentBalancePagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PaymentBalanceListForReport>
	 */
	List<PaymentBalanceListForReport> getListForReport(
			final PaymentBalanceListConditionForReport condition);
}
