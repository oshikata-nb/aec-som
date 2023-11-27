/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.shipping;

import java.util.List;

/**
 * 出荷指図－運送会社Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingCarryForComboboxesDao {

	/** BEANアノテーション */
	Class<ShippingCarryForComboboxes> BEAN =
		com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxes.class;

    /**
	 * 出荷指図－運送会社一覧取得
	 * @return List<ShippingCarryForComboboxes> 検索結果リスト
	 */
	List<ShippingCarryForComboboxes> getCarryList();

}
