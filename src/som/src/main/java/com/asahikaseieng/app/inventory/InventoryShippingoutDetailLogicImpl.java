/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;

import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail.InventoryShippingoutDetail;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail.InventoryShippingoutDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;

/**
 * ロジック 実装クラス. 在庫出庫詳細画面
 * @author tanaka
 */
public class InventoryShippingoutDetailLogicImpl implements
		InventoryShippingoutDetailLogic {

	private ZaiCtrlDao zaiCtrlDao;

	private InventoryShippingoutDetailDao inventoryShippingoutDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	private ItemQueueDetailDao itemQueueDetailDao;

	private ItemDetailDao itemDetailDao;

	private ReasonDetailDao reasonDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryShippingoutDetailLogicImpl() {
	}

	/**
	 * エンティティ取得．
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryShippingoutDetail InventoryShippingoutDetail
	 */
	public InventoryShippingoutDetail getDetailEntity(final String locationCd,
			final String itemCd, final String lotNo) {
		InventoryShippingoutDetail bean = inventoryShippingoutDetailDao
				.getEntity(locationCd, itemCd, lotNo);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueDetail
	 */
	public ItemQueueDetail getItemQueueEntity(final String itemCd) {
		/* 最新バージョン取得 */
		ItemQueueLastVersion beanLast = itemQueueLastVersionDao
				.getLastVersion(itemCd);

		ItemQueueDetail beanDetail = new ItemQueueDetail();

		if (beanLast != null) {
			/* 品目取得 */
			beanDetail = itemQueueDetailDao.getEntity(itemCd, beanLast
					.getVersion());
		}

		return beanDetail;
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
	 * @param frm InventoryShippingoutDetailForm
	 * @param typeDivision typeDivision
	 * @param tantoCd 担当者コード
	 */
	public void stockUpdate(final InventoryShippingoutDetailForm frm,
			final BigDecimal typeDivision, final String tantoCd) {
		Stockinout stockinout = new Stockinout(zaiCtrlDao);
		BigDecimal qty = new BigDecimal("0");

		if (typeDivision.equals(new BigDecimal("1"))
				|| typeDivision.equals(new BigDecimal("3"))) {
			/* 品目が原料 or 中間品の場合 */
			qty = frm.getInventoryQty(); /* 在庫重量 */
		} else {
			qty = frm.getPackQty(); /* 荷姿数量 */
		}

		/* 例外出庫処理 */
		if (!stockinout.delivery(frm.getItemCd(), qty, frm.getLastInDate(), frm
				.getLocationCd(), frm.getLotNo(), frm.getRemark(), frm
				.getRyDescription(), frm.getRyCd(), tantoCd)) {
			throw new LogicExceptionEx();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * InventoryShippingoutDetailDaoを設定します。
	 * @param inventoryShippingoutDetailDao InventoryShippingoutDetailDao
	 */
	public void setInventoryShippingoutDetailDao(
			final InventoryShippingoutDetailDao inventoryShippingoutDetailDao) {
		this.inventoryShippingoutDetailDao = inventoryShippingoutDetailDao;
	}

	/**
	 * zaiCtrlDaoを設定します。
	 * @param zaiCtrlDao zaiCtrlDao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * reasonDetailDaoを設定します。
	 * @param reasonDetailDao reasonDetailDao
	 */
	public void setReasonDetailDao(final ReasonDetailDao reasonDetailDao) {
		this.reasonDetailDao = reasonDetailDao;
	}

	/**
	 * itemQueueLastVersionDaoを設定します。
	 * @param itemQueueLastVersionDao itemQueueLastVersionDao
	 */
	public void setItemQueueLastVersionDao(
			final ItemQueueLastVersionDao itemQueueLastVersionDao) {
		this.itemQueueLastVersionDao = itemQueueLastVersionDao;
	}

	/**
	 * itemQueueDetailDaoを設定します。
	 * @param itemQueueDetailDao itemQueueDetailDao
	 */
	public void setItemQueueDetailDao(
			final ItemQueueDetailDao itemQueueDetailDao) {
		this.itemQueueDetailDao = itemQueueDetailDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}
}
