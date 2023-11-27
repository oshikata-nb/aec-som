/*
 * Created on 2009/05/28
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.directionstatuschange;

import java.util.List;

/**
 * 製造指図ステータス変更－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface DirectionStatusChangeLineForComboboxesDao {

	/** BEANアノテーション */
	Class<DirectionStatusChangeLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.directionstatuschange.DirectionStatusChangeLineForComboboxes.class;

    /**
	 * 製造指図ステータス変更－生産ライン一覧取得	 * @return List<DirectionStatusChangeLineForComboboxes> 検索結果リスト	 */
	List<DirectionStatusChangeLineForComboboxes> findAll();

}
