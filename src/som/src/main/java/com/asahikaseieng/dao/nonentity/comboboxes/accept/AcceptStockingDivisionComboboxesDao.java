/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.accept;

import java.util.List;

/**
 * 受入・仕入－仕入区分Daoインターフェース.
 *
 * @author tosco
 */
public interface AcceptStockingDivisionComboboxesDao {

	/** BEANアノテーション */
	Class<AcceptStockingDivisionComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxes.class;

    /**
	 * 受入・仕入－仕入区分一覧取得
	 * @return List<AcceptStockingDivisionComboboxes> 検索結果リスト
	 */
	List<AcceptStockingDivisionComboboxes> findStockingDivision();

}
