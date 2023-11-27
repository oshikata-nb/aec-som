/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.sales;

import java.util.List;

/**
 * 売上－入庫ロケーションDaoインターフェース.
 *
 * @author tosco
 */
public interface SalesHousingLocationForComboboxesDao {

	/** BEANアノテーション */
	Class<SalesHousingLocationForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesHousingLocationForComboboxes.class;

    /**
	 * 売上－入庫ロケーション(通常用)一覧取得
	 * @return List<RecipeHeader> 検索結果リスト
	 */
	List<SalesHousingLocationForComboboxes> getHousingLocationForStandardList();

    /**
	 * 売上－入庫ロケーション(預り品用)一覧取得
	 * @return List<RecipeHeader> 検索結果リスト
	 */
	List<SalesHousingLocationForComboboxes> getHousingLocationForKeepList();

}
