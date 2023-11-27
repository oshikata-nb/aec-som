/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.accept.AcceptList;
import com.asahikaseieng.dao.nonentity.accept.AcceptListPagerCondition;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListConditionForReport;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受入・仕入検索画面 ロジッククラス interface.
 * @author tosco
 */
public interface AcceptListLogic {

	/**
	 * 仕入区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createAcceptStockingDivisionCombobox();

	/**
	 * 購買オーダーテーブルの検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<AcceptList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<AcceptList> getSearchList(final AcceptListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票Excel用データ検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<AcceptListForReport> 検索結果リスト
	 */
	List<AcceptListForReport> getReportList(
			final AcceptListConditionForReport condition);
}
