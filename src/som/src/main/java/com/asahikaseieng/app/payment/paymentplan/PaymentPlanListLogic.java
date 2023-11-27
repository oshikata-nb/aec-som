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
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanList;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * PaymentPlanListLogicクラス．入金予定一覧
 * @author tosco
 */
public interface PaymentPlanListLogic {

	/**
	 * 検索処理を行う.入金予定一覧
	 * @param condition condition
	 * @return List<PaymentPlanList> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<PaymentPlanList> getSearchList(
			final PaymentPlanPagerCondition condition) throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PaymentPlanListForReport>
	 */
	List<PaymentPlanListForReport> getListForReport(
			final PaymentPlanListConditionForReport condition);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);
}
