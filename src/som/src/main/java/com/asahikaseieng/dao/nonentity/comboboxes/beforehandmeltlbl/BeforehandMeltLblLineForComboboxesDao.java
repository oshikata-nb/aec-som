/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.beforehandmeltlbl;

import java.util.List;

/**
 * 予備溶解ラベル発行－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface BeforehandMeltLblLineForComboboxesDao {

	/** BEANアノテーション */
	Class<BeforehandMeltLblLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.beforehandmeltlbl.BeforehandMeltLblLineForComboboxes.class;

    /**
	 * 予備溶解ラベル発行－生産ライン一覧取得	 * @return List<BeforehandMeltLblLineForComboboxes> 検索結果リスト	 */
	List<BeforehandMeltLblLineForComboboxes> findAll();

}
