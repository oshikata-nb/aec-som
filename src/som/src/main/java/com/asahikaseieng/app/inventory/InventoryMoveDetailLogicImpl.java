/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.dao.nonentity.inventorymovedetail.InventoryMoveDetail;
import com.asahikaseieng.dao.nonentity.inventorymovedetail.InventoryMoveDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetailDao;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * ロジック 実装クラス. 在庫移動詳細画面
 * @author tanaka
 */
public class InventoryMoveDetailLogicImpl implements InventoryMoveDetailLogic {

	private ZaiCtrlDao zaiCtrlDao;

	private InventoryMoveDetailDao inventoryMoveDetailDao;

	private LocationDetailDao locationDetailDao;

	private ReasonDefaultDetailDao reasonDefaultDetailDao;

	private ReasonDetailDao reasonDetailDao;

	private PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao;

	private ItemDetailDao itemDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryMoveDetailLogicImpl() {
	}

	/**
	 * エンティティ取得．
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryMoveDetail InventoryMoveDetail
	 */
	public InventoryMoveDetail getDetailEntity(final String locationCd,
			final String itemCd, final String lotNo) {
		InventoryMoveDetail bean = inventoryMoveDetailDao.getEntity(locationCd,
			itemCd, lotNo);
		return bean;
	}

	/**
	 * 入庫ロケーション検索
	 * @param locationCd ロケーションコード
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
	 * 理由デフォルト検索
	 * @return ReasonDefaultDetail
	 */
	public ReasonDefaultDetail getReasonDefaultEntity() {
		ReasonDefaultDetail bean = reasonDefaultDetailDao.getEntity();
		return bean;
	}

	/**
	 * 在庫更新
	 * @param frm InventoryMoveDetailForm
	 * @param typeDivision typeDivision
	 * @param tantoCd 担当者コード
	 */
	public void stockUpdate(final InventoryMoveDetailForm frm,
			final BigDecimal typeDivision, final String tantoCd) {
		Stockinout stockinout = new Stockinout(zaiCtrlDao);
		BigDecimal qty = new BigDecimal("0");

		if (typeDivision.equals(new BigDecimal("1"))
				|| typeDivision.equals(new BigDecimal("2"))
				|| typeDivision.equals(new BigDecimal("3"))) {
			/* 品目が原料 or 包材 or 中間品の場合 */
			qty = frm.getInventoryQty(); /* 在庫重量 */
		} else {
			qty = frm.getPackQty(); /* 荷姿数量 */
		}

		/* 例外出庫処理 */
		if (!stockinout.delivery(frm.getItemCd(), qty, frm.getLastInDate(), frm
				.getOutLocationCd(), frm.getLotNo(), frm.getRemark(), frm
				.getRyDescription(), frm.getRyCd(), tantoCd)) {
			throw new LogicExceptionEx();
		}

		/* 例外入庫処理 */
		if (!stockinout.stock(frm.getItemCd(), qty, frm.getLastInDate(), frm
				.getInLocationCd(), frm.getLotNo(), frm.getRemark(), frm
				.getRyDescription(), frm.getRyCd(), frm.getAliasLotNo(), null,
			tantoCd)) {
			throw new LogicExceptionEx();
		}
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

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	public ItemDetail getItemEntity(final String itemCd) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, null);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * InventoryMoveDetailDaoを設定します。
	 * @param inventoryMoveDetailDao InventoryMoveDetailDao
	 */
	public void setInventoryMoveDetailDao(
			final InventoryMoveDetailDao inventoryMoveDetailDao) {
		this.inventoryMoveDetailDao = inventoryMoveDetailDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao procedureCallDao) {
		this.zaiCtrlDao = procedureCallDao;
	}

	/**
	 * reasonDetailDaoを設定します。
	 * @param reasonDetailDao reasonDetailDao
	 */
	public void setReasonDetailDao(final ReasonDetailDao reasonDetailDao) {
		this.reasonDetailDao = reasonDetailDao;
	}

	/**
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao locationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}

	/**
	 * reasonDefaultDetailDaoを設定します。
	 * @param reasonDefaultDetailDao reasonDefaultDetailDao
	 */
	public void setReasonDefaultDetailDao(
			final ReasonDefaultDetailDao reasonDefaultDetailDao) {
		this.reasonDefaultDetailDao = reasonDefaultDetailDao;
	}

	/**
	 * purchaseAttributeQueueDetailDaoを設定します。
	 * @param purchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao
	 */
	public void setPurchaseAttributeQueueDetailDao(
			final PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao) {
		this.purchaseAttributeQueueDetailDao = purchaseAttributeQueueDetailDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}
}
