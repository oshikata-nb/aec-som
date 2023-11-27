/*
 * Created on 2008/06/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationList;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationListDao;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 分類マスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class ClassificationListLogicImpl implements ClassificationListLogic {

	private ClassificationListDao classificationListDao;

	private ClassificationListForComboboxesDao classificationListForComboboxesDao;

	/**
	 * コンストラクタ.分類マスタ
	 */
	public ClassificationListLogicImpl() {
	}

	/**
	 * 検索条件.全検索 {@inheritDoc}
	 */
	public List<ClassificationList> getSearchList(
			final ClassificationPagerCondition condition)
			throws NoDataException {
		checkParams(condition);

		List<ClassificationList> list = classificationListDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 
	 * @param dataType dataType
	 * @return List<ClassificationListForComboboxes>
	 * @throws NoDataException NoDataException
	 */
	public List<ClassificationListForComboboxes> getCategoryComboBoxList(
			final BigDecimal dataType) throws NoDataException {

		List<ClassificationListForComboboxes> list = classificationListForComboboxesDao
				.getListForComboboxes(dataType, null);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ClassificationPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * classificationListDaoを設定します。
	 * @param classificationListDao classificationListDao
	 */
	public void setClassificationListDao(
			final ClassificationListDao classificationListDao) {
		this.classificationListDao = classificationListDao;

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
