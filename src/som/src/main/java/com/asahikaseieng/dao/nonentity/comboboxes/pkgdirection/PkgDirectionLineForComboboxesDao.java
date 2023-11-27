/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection;

import java.util.List;

/**
 * 包装指図－生産ラインコンボボックスDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionLineForComboboxesDao {

	/** BEANアノテーション */
	Class<PkgDirectionLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionLineForComboboxes.class;

    /**
	 * 包装指図－生産ライン一覧取得
	 * @return List<PkgDirectionLineForComboboxes> 検索結果リスト
	 */
	List<PkgDirectionLineForComboboxes> findAll();

}
