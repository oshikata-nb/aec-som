/*
 * Created on 2009/03/26
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.materialrinput;

import java.util.List;

import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputListPagerCondition;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListConditionForReport;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 外注原材料投入実績一覧 ロジッククラス interface.
 * @author tosco
 */
public interface MaterialRinputListLogic {

	/**
	 * 購買オーダーテーブルの検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<MaterialRinputList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<MaterialRinputList> getSearchList(
			final MaterialRinputListPagerCondition condition)
			throws NoDataException;

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<MaterialRinputListForReport> 検索結果リスト
	 */
	List<MaterialRinputListForReport> getReportList(
			final MaterialRinputListConditionForReport condition);

}
