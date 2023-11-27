/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.shippingresult;

import java.util.List;

/**
 * 出荷実績－運送会社Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingResultCarryForComboboxesDao {

	/** BEANアノテーション */
	Class<ShippingResultCarryForComboboxes> BEAN = com.asahikaseieng.dao.nonentity.comboboxes
			.shippingresult.ShippingResultCarryForComboboxes.class;

    /**
	 * 出荷実績－運送会社一覧取得
	 * @return List<ShippingResultCarryForComboboxes> 検索結果リスト
	 */
	List<ShippingResultCarryForComboboxes> getCarryList();

}
