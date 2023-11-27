/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.stockbooking;

import java.util.List;

/**
 * 検査待ち在庫計上－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface StockBookingLineForComboboxesDao {

	/** BEANアノテーション */
	Class<StockBookingLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.stockbooking.StockBookingLineForComboboxes.class;

    /**
	 * 検査待ち在庫計上－生産ライン一覧取得	 * @return List<StockBookingLineForComboboxes> 検索結果リスト	 */
	List<StockBookingLineForComboboxes> findAll();

}
