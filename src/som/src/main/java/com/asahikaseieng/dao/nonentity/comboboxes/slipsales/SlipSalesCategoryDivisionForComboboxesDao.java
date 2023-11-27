/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.slipsales;

import java.util.List;

/**
 * 売上伝票出力－売上区分Daoインターフェース.
 *
 * @author tosco
 */
public interface SlipSalesCategoryDivisionForComboboxesDao {

	/** BEANアノテーション */
	Class<SlipSalesCategoryDivisionForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCategoryDivisionForComboboxes.class;

    /**
	 * 売上伝票出力－売上区分一覧取得
	 * @return List<SlipSalesCategoryDivisionForComboboxes> 検索結果リスト
	 */
	List<SlipSalesCategoryDivisionForComboboxes> getCategoryDivisionList();

}
