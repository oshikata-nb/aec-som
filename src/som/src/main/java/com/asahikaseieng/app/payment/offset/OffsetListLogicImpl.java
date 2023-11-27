/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetList;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetListDao;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListForReport;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * OffsetListLogicImplクラス.グループ間相殺入力一覧
 * @author tosco
 */
public class OffsetListLogicImpl implements OffsetListLogic {

	private OffsetListDao offsetListDao;

	private OffsetListForReportDao offsetListForReportDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/**
	 * コンストラクタ.グループ間相殺入力一覧
	 */
	public OffsetListLogicImpl() {
	}

	/**
	 * 検索処理を行う.グループ間相殺入力一覧
	 * @param condition condition
	 * @return List<OffsetList> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public List<OffsetList> getSearchList(final OffsetPagerCondition condition)
			throws NoDataException {

		final List<OffsetList> bean = offsetListDao.getSearchList(condition);

		if (bean.isEmpty()) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * グループ間相殺一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<OffsetListForReport>
	 */
	public List<OffsetListForReport> getListForReport(
			final OffsetListConditionForReport condition) {
		List<OffsetListForReport> list = offsetListForReportDao
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
	 * OffsetListDaoを設定します。
	 * 
	 * @param offsetListDao offsetListDao
	 */
	public void setOffsetListDao(final OffsetListDao offsetListDao) {
		this.offsetListDao = offsetListDao;
	}

	/**
	 * offsetListForReportDaoを設定します。
	 * @param offsetListForReportDao offsetListForReportDao
	 */
	public void setOffsetListForReportDao(
			final OffsetListForReportDao offsetListForReportDao) {
		this.offsetListForReportDao = offsetListForReportDao;
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
