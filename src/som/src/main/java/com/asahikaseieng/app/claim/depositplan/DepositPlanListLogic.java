/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.depositplan;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanList;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * BalanceListLogicクラス．入金予定一覧
 * @author tosco
 */
public interface DepositPlanListLogic {

	/**
	 * 検索処理を行う.入金予定一覧
	 * @param condition condition
	 * @return List<DepositPlanList> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<DepositPlanList> getSearchList(
			final DepositPlanListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<DepositPlanListForReport>
	 */
	List<DepositPlanListForReport> getListForReport(
			final DepositPlanListConditionForReport condition);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);
}
