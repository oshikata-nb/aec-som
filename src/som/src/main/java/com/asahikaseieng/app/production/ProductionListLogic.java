/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.production;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.production.ProductionList;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionDetailListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionHeaderListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionListConditionForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産計画入力一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ProductionListLogic {

	/**
	 * 検索処理を行う
	 * @param condition 検索条件
	 * @return List<ProductionList> 生産計画入力一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<ProductionList> getSearchList(final ProductionPagerCondition condition)
			throws NoDataException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<ProductionLineForComboboxes>
	 */
	List<ProductionLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 帳票Excelヘッダ
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 */
	List<ProductionHeaderListForReport> getHeaderList(
			final ProductionListConditionForReport condition);

	/**
	 * 帳票Excel詳細
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 */
	List<ProductionDetailListForReport> getDetailList(
			final ProductionListConditionForReport condition);
}
