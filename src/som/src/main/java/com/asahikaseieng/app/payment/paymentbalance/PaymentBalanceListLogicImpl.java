/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceList;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalanceListDao;
import com.asahikaseieng.dao.nonentity.payment.paymentbalance.PaymentBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * 支払残高一覧ロジッククラス
 * @author tosco
 */
public class PaymentBalanceListLogicImpl implements PaymentBalanceListLogic {

	private PaymentBalanceListDao paymentBalanceListDao;

	private PaymentBalanceListForReportDao paymentBalanceListForReportDao;

	/**
	 * コンストラクタ.支払残高一覧ロジック
	 */
	public PaymentBalanceListLogicImpl() {
	}

	/**
	 * 検索処理を行う.支払残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<PaymentBalanceList> getSearchList(
			final PaymentBalancePagerCondition condition)
			throws NoDataException {

		checkParams(condition);

		final List<PaymentBalanceList> bean = paymentBalanceListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final PaymentBalancePagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 支払残高一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<PaymentBalanceListForReport>
	 */
	public List<PaymentBalanceListForReport> getListForReport(
			final PaymentBalanceListConditionForReport condition) {
		List<PaymentBalanceListForReport> list = paymentBalanceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentBalanceListDaoを設定します。
	 * 
	 * @param paymentBalanceListDao paymentBalanceListDao
	 */
	public void setPaymentBalanceListDao(
			final PaymentBalanceListDao paymentBalanceListDao) {
		this.paymentBalanceListDao = paymentBalanceListDao;
	}

	/**
	 * paymentBalanceListForReportDaoを設定します。
	 * @param paymentBalanceListForReportDao paymentBalanceListForReportDao
	 */
	public void setPaymentBalanceListForReportDao(
			final PaymentBalanceListForReportDao paymentBalanceListForReportDao) {
		this.paymentBalanceListForReportDao = paymentBalanceListForReportDao;
	}
}
