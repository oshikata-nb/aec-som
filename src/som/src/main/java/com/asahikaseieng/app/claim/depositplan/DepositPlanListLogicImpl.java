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
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanListDao;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListForReport;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * DepositPlanListLogicImplクラス.入金予定一覧
 * @author tosco
 */
public class DepositPlanListLogicImpl implements DepositPlanListLogic {

	private DepositPlanListDao depositPlanListDao;

	private DepositPlanListForReportDao depositPlanListForReportDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/**
	 * コンストラクタ.入金予定一覧ロジック
	 */
	public DepositPlanListLogicImpl() {
	}

	/**
	 * 検索処理を行う.入金予定一覧
	 * @param condition condition
	 * @return List<DepositPlanList> 詳細データリスト
	 * @throws NoDataException NoDataException
	 */
	public List<DepositPlanList> getSearchList(
			final DepositPlanListPagerCondition condition)
			throws NoDataException {

		final List<DepositPlanList> bean = depositPlanListDao
				.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 入金予定一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<DepositPlanListForReport>
	 */
	public List<DepositPlanListForReport> getListForReport(
			final DepositPlanListConditionForReport condition) {
		List<DepositPlanListForReport> list = depositPlanListForReportDao
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
				.getListForComboboxes(dataType, BigDecimal.ONE);
		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * depositPlanListDaoを設定します。
	 * 
	 * @param depositPlanListDao depositPlanListDao
	 */
	public void setDepositPlanListDao(
			final DepositPlanListDao depositPlanListDao) {
		this.depositPlanListDao = depositPlanListDao;
	}

	/**
	 * depositPlanListForReportDaoを設定します。
	 * @param depositPlanListForReportDao depositPlanListForReportDao
	 */
	public void setDepositPlanListForReportDao(
			final DepositPlanListForReportDao depositPlanListForReportDao) {
		this.depositPlanListForReportDao = depositPlanListForReportDao;
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
