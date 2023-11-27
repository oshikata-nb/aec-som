/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.buying;

import java.util.List;

/**
 * 仕入－仕入区分Daoインターフェース.
 *
 * @author tosco
 */
public interface BuyingStockingDivisionComboboxesDao {

	/** BEANアノテーション */
	Class<BuyingStockingDivisionComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes.class;

	/** ARGSアノテーション getSearchTranslateList */
	String findStockingDivision_ARGS = "cancel";
    /**
	 * 仕入－仕入区分一覧取得
	 * @param cancel 区分に取消を含めるかどうか ture:取消を含める false:取消を含めない
	 * @return List<BuyingStockingDivisionComboboxes> 検索結果リスト
	 */
	List<BuyingStockingDivisionComboboxes> findStockingDivision(final boolean cancel);

}
