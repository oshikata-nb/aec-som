/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.tanklock;

import java.util.List;

/**
 * 調合タンク底弁インターロック解除/再設定－生産ラインDaoインターフェース.
 *
 * @author tosco
 */
public interface TankLockLineForComboboxesDao {

	/** BEANアノテーション */
	Class<TankLockLineForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.tanklock.TankLockLineForComboboxes.class;

    /**
	 * 調合タンク底弁インターロック解除/再設定－生産ライン一覧取得	 * @return List<TankLockLineForComboboxes> 検索結果リスト	 */
	List<TankLockLineForComboboxes> findAll();

}
