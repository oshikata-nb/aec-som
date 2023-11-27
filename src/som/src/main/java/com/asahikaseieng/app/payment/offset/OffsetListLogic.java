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
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetList;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * OffsetListLogicクラス．グループ間相殺入力
 * @author tosco
 */
public interface OffsetListLogic {

	/**
	 * 検索処理を行う.グループ間相殺入力
	 * @param condition condition
	 * @return List<OffsetList> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<OffsetList> getSearchList(final OffsetPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OffsetListForReport>
	 */
	List<OffsetListForReport> getListForReport(
			final OffsetListConditionForReport condition);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);
}
