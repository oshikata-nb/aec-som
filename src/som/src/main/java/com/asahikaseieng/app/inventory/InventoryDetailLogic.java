/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate.InventoryDetailLotDuplicate;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;

/**
 * 在庫入庫入力ロジック interface.
 * @author t0011036
 */
public interface InventoryDetailLogic {
	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);

	/**
	 * ロケーション検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getLocationEntity(final String locationCd);

	/**
	 * 理由検索処理を行う.
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	ReasonDetail getReasonEntity(final String ryCd);

	/**
	 * 在庫更新
	 * @param frm 登録データ
	 * @param typeDivision 種別
	 * @param inventoryQty 在庫重量
	 * @param tantoCd 担当者コード
	 */
	void stockUpdate(final InventoryDetailForm frm,
			final BigDecimal typeDivision, final BigDecimal inventoryQty,
			final String tantoCd);

	/**
	 * 入荷ロット番号/方操作合図番号の重複tチェック
	 * @param lotNo 入荷ロット番号/包装指図番号
	 * @return InventoryDetailLotDuplicate
	 */
	InventoryDetailLotDuplicate getLotDuplicate(final String lotNo);

	/**
	 * 販売品属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueueDetail
	 */
	PurchaseAttributeQueueDetail getPurchaseEntity(final String itemCd,
			final BigDecimal version);
}
