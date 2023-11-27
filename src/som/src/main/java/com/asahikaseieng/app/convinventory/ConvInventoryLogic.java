/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.convinventory;

import java.math.BigDecimal;

/**
 * 在庫数量換算
 * @author t0011036
 */
public interface ConvInventoryLogic {
	/**
	 * 在庫数量を荷姿数と端数に換算する
	 * @param itemCd 品目コード
	 * @param inventoryQty 在庫数量
	 * @return convInventoryResult convInventoryResult
	 */
	ConvInventoryResult inventoryToPack(String itemCd, BigDecimal inventoryQty);

	/**
	 * 荷姿数と端数を在庫数量に換算する
	 * @param itemCd 品目コード
	 * @param packQty 荷姿数
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	ConvInventoryResult packToInventory(String itemCd, BigDecimal packQty,
			BigDecimal fractionQty);

	/**
	 * 荷姿数と端数を重量に換算する
	 * @param itemCd 品目コード
	 * @param packQty 荷姿数
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	ConvInventoryResult packToWeight(String itemCd, BigDecimal packQty,
			BigDecimal fractionQty);

	/**
	 * 端数入力チェック
	 * @param itemCd 品目コード
	 * @param fractionQty 端数数量
	 * @return convInventoryResult convInventoryResult
	 */
	ConvInventoryResult checkInputFraction(String itemCd, BigDecimal fractionQty);
}
