/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buying;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buying.BuyingList;
import com.asahikaseieng.dao.nonentity.buying.BuyingPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListConditionForReport;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入一覧 ロジッククラス interface.
 * @author tosco
 */
public interface BuyingListLogic {

	/**
	 * 検索処理を行う
	 * @param condition 検索条件
	 * @return List<BuyingList> 仕入一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<BuyingList> getSearchList(final BuyingPagerCondition condition)
			throws NoDataException;

	/**
	 * 仕入区分コンボボックスを取得する
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes();

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createBuyingStockingDivisionCombobox(final boolean zero);

	/**
	 * 一覧検索処理
	 * @param condition 検索条件
	 * @return List<BuyingList> 検索結果リスト
	 */
	List<BuyingListForReport> getReportList(
			final BuyingListConditionForReport condition);

	/**
	 * 伝票発行フラグ更新
	 * @param tantoCd tantoCd
	 * @param buyingNo buyingNo
	 */
	void updateBuying(final String tantoCd, final ArrayList<String> buyingNo);

}
