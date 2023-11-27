/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection;

import java.util.List;

/**
 * 包装実績－生産ラインコンボボックスDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgRdirectionLineForComboboxesDao {

	/** BEANアノテーション */
	Class<PkgRdirectionLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionLineForComboboxes.class;

    /**
	 * 包装指図－生産ライン一覧取得
	 * @return List<PkgRdirectionLineForComboboxes> 検索結果リスト
	 */
	List<PkgRdirectionLineForComboboxes> findAll();

}
