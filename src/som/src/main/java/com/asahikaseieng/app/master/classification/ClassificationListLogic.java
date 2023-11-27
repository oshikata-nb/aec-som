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
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationList;
import com.asahikaseieng.dao.nonentity.master.classification.ClassificationPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 分類マスタ一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ClassificationListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ClassificationList>
	 */
	List<ClassificationList> getSearchList(
			final ClassificationPagerCondition condition)
			throws NoDataException;

	/**
	 * Listメソッド
	 * @param dataType dataType
	 * @return List<ClassificationListForComboboxes>
	 * @throws NoDataException NoDataException
	 */
	List<ClassificationListForComboboxes> getCategoryComboBoxList(
			final BigDecimal dataType) throws NoDataException;

}
