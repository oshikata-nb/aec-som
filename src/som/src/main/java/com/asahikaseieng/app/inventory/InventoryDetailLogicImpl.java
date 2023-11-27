/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate.InventoryDetailLotDuplicate;
import com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate.InventoryDetailLotDuplicateDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * 在庫入庫入力ロジック 実装クラス.
 * @author t0011036
 */
public class InventoryDetailLogicImpl implements InventoryDetailLogic {

	private ZaiCtrlDao zaiCtrlDao;

	private ItemDetailDao itemDetailDao;

	private LocationDetailDao locationDetailDao;

	private ReasonDetailDao reasonDetailDao;

	private InventoryDetailLotDuplicateDao inventoryDetailLotDuplicateDao;

	private PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryDetailLogicImpl() {
		super();
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	public ItemDetail getItemEntity(final String itemCd) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, null);
		return bean;
	}

	/**
	 * ロケーション検索
	 * @param locationCd ロケションコード
	 * @return LocationDetail
	 */
	public LocationDetail getLocationEntity(final String locationCd) {
		LocationDetail bean = locationDetailDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * 理由検索
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	public ReasonDetail getReasonEntity(final String ryCd) {
		ReasonDetail bean = reasonDetailDao.getEntity(ryCd);
		return bean;
	}

	/**
	 * 在庫更新
	 * @param frm 登録データ
	 * @param typeDivision 種別
	 * @param inventoryQty 在庫重量
	 * @param tantoCd 担当者コード
	 */
	public void stockUpdate(final InventoryDetailForm frm,
			final BigDecimal typeDivision, final BigDecimal inventoryQty,
			final String tantoCd) {
		Stockinout stockinout = new Stockinout(zaiCtrlDao);
		BigDecimal qty = new BigDecimal("0");

		if (typeDivision.equals(new BigDecimal("1"))
				|| typeDivision.equals(new BigDecimal("3"))) {
			/* 品目が原料 or 中間品の場合 */
			// qty = frm.getInventoryQty(); /* 在庫重量 */
			qty = inventoryQty; /* 在庫重量 */
		} else {
			qty = frm.getPackQty(); /* 荷姿数量 */
		}

		/* 例外入庫処理 */
		if (!stockinout.stock(frm.getItemCd(), qty, frm.getLastInDate(), frm
				.getLocationCd(), frm.getLotNo(), frm.getRemark(), frm
				.getRyDescription(), frm.getRyCd(), frm.getAliasLotNo(), frm
				.getInventoryCost(), tantoCd)) {

			throw new LogicExceptionEx();
		}
	}

	/**
	 * 入荷ロット番号/方操作合図番号の重複tチェック
	 * @param lotNo 入荷ロット番号/包装指図番号
	 * @return InventoryDetailLotDuplicate
	 */
	public InventoryDetailLotDuplicate getLotDuplicate(final String lotNo) {
		InventoryDetailLotDuplicate bean = inventoryDetailLotDuplicateDao
				.getEntity(lotNo);
		return bean;
	}

	/**
	 * 販売品属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueueDetail
	 */
	public PurchaseAttributeQueueDetail getPurchaseEntity(final String itemCd,
			final BigDecimal version) {
		PurchaseAttributeQueueDetail bean = purchaseAttributeQueueDetailDao
				.getEntity(itemCd, version);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao locationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}

	/**
	 * reasonDetailDaoを設定します。
	 * @param reasonDetailDao reasonDetailDao
	 */
	public void setReasonDetailDao(final ReasonDetailDao reasonDetailDao) {
		this.reasonDetailDao = reasonDetailDao;
	}

	/**
	 * inventoryDetailLotDuplicateDaoを設定します。
	 * @param inventoryDetailLotDuplicateDao inventoryDetailLotDuplicateDao
	 */
	public void setInventoryDetailLotDuplicateDao(
			final InventoryDetailLotDuplicateDao inventoryDetailLotDuplicateDao) {
		this.inventoryDetailLotDuplicateDao = inventoryDetailLotDuplicateDao;
	}

	/**
	 * zaiCtrlDaoを設定します。
	 * @param zaiCtrlDao zaiCtrlDao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * purchaseAttributeQueueDetailDaoを設定します。
	 * @param purchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao
	 */
	public void setPurchaseAttributeQueueDetailDao(
			final PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao) {
		this.purchaseAttributeQueueDetailDao = purchaseAttributeQueueDetailDao;
	}
}
