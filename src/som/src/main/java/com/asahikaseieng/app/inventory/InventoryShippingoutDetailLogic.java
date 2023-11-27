/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail.InventoryShippingoutDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;

/**
 * 在庫出庫詳細画面 ロジック interface.
 * @author FPC
 */
public interface InventoryShippingoutDetailLogic {
	/**
	 * 詳細処理を行う．
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryShippingoutDetail InventoryShippingoutDetail
	 */
	InventoryShippingoutDetail getDetailEntity(final String locationCd,
			final String itemCd, final String lotNo);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueDetail
	 */
	ItemQueueDetail getItemQueueEntity(final String itemCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);

	/**
	 * 理由検索処理を行う.
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	ReasonDetail getReasonEntity(final String ryCd);

	/**
	 * 在庫更新
	 * @param frm InventoryShippingoutDetailForm
	 * @param typeDivision typeDivision
	 * @param tantoCd tantoCd
	 */
	void stockUpdate(final InventoryShippingoutDetailForm frm,
			final BigDecimal typeDivision, final String tantoCd);
}
