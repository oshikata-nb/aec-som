/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentplan;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanList;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanListDao;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * BalanceListLogicImplクラス.支払残高一覧
 * @author tosco
 */
public class PaymentPlanListLogicImpl implements PaymentPlanListLogic {

	private PaymentPlanListDao paymentPlanListDao;

	private PaymentPlanListForReportDao paymentPlanListForReportDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/**
	 * コンストラクタ.支払残高一覧ロジック
	 */
	public PaymentPlanListLogicImpl() {
	}

	/**
	 * 検索処理を行う.支払残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<PaymentPlanList> getSearchList(
			final PaymentPlanPagerCondition condition) throws NoDataException {

		final List<PaymentPlanList> bean = paymentPlanListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 支払予定一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<PaymentPlanListForReport>
	 */
	public List<PaymentPlanListForReport> getListForReport(
			final PaymentPlanListConditionForReport condition) {
		List<PaymentPlanListForReport> list = paymentPlanListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 分類リスト取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	public List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType) {
		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, null);
		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentPlanListDaoを設定します。
	 * 
	 * @param paymentPlanListDao paymentPlanListDao
	 */
	public void setPaymentPlanListDao(
			final PaymentPlanListDao paymentPlanListDao) {
		this.paymentPlanListDao = paymentPlanListDao;
	}

	/**
	 * paymentPlanListForReportDaoを設定します。
	 * @param paymentPlanListForReportDao paymentPlanListForReportDao
	 */
	public void setPaymentPlanListForReportDao(
			final PaymentPlanListForReportDao paymentPlanListForReportDao) {
		this.paymentPlanListForReportDao = paymentPlanListForReportDao;
	}

	/**
	 * classificationListForComboboxesDaoを設定します。
	 * @param classificationListForComboboxesDao
	 *            classificationListForComboboxesDao
	 */
	public void setClassificationListForComboboxesDao(
			final ClassificationListForComboboxesDao classificationListForComboboxesDao) {
		this.classificationListForComboboxesDao = classificationListForComboboxesDao;
	}
}
