/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.midinspectcomp;

import java.util.List;

/**
 * 中間品検査完了入力－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface MidInspectCompLineForComboboxesDao {

	/** BEANアノテーション */
	Class<MidInspectCompLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.midinspectcomp.MidInspectCompLineForComboboxes.class;

    /**
	 * 中間品検査完了入力－生産ライン一覧取得	 * @return List<MidInspectCompLineForComboboxes> 検索結果リスト	 */
	List<MidInspectCompLineForComboboxes> findAll();

}
