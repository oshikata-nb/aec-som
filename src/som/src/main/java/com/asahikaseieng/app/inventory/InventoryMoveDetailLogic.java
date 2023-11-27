/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.inventorymovedetail.InventoryMoveDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;

/**
 * 在庫移動詳細画面 ロジック interface.
 * @author FPC
 */
public interface InventoryMoveDetailLogic {
	/**
	 * 詳細処理を行う．
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryMoveDetail InventoryMoveDetail
	 */
	InventoryMoveDetail getDetailEntity(final String locationCd,
			final String itemCd, final String lotNo);

	/**
	 * 入庫ロケーション検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getLocationEntity(final String locationCd);

	/**
	 * 理由デフォルト検索処理を行う.
	 * @return ReasonDefaultDetail
	 */
	ReasonDefaultDetail getReasonDefaultEntity();

	/**
	 * 理由検索処理を行う.
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	ReasonDetail getReasonEntity(final String ryCd);

	/**
	 * 在庫更新
	 * @param frm InventoryMoveDetailForm
	 * @param typeDivision typeDivision
	 * @param tantoCd tantoCd
	 */
	void stockUpdate(final InventoryMoveDetailForm frm,
			final BigDecimal typeDivision, final String tantoCd);

	/**
	 * 販売品属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueueDetail
	 */
	PurchaseAttributeQueueDetail getPurchaseEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);
}
