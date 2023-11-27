/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.entity.inventorycount.InventoryCount;
import com.asahikaseieng.dao.entity.inventorycount.InventoryCountDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCountDao;
import com.asahikaseieng.dao.nonentity.inquiryupdatelist.InquiryUpdateList;
import com.asahikaseieng.dao.nonentity.inquiryupdatelist.InquiryUpdateListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 棚卸更新処理ロジック 実装クラス.
 * @author tanaka
 */
public class InquiryUpdateLogicImpl implements InquiryUpdateLogic {

	private InquiryInventoryCountDao inquiryInventoryCountDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private ZaiCtrlDao zaiCtrlDao;

	private InquiryUpdateListDao inquiryUpdateListDao;

	private InventoryCountDao inventoryCountDao;

	private ConvInventoryLogic convInventoryLogic;

	/**
	 * コンストラクタ
	 */
	public InquiryUpdateLogicImpl() {
	}

	/**
	 * 棚卸リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getCountDivisionList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao
				.getListForComboboxes("TANA");
		return list;
	}

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @param countLocation ロケーションコード
	 * @param itemCd 品目コード
	 * @param lotNo ロット番号
	 * @param countUpdateDate 棚卸更新日
	 * @return InquiryInventoryCount
	 */
	public InquiryInventoryCount getInventoryCount(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo,
			final Timestamp countUpdateDate) {
		InquiryInventoryCount bean = inquiryInventoryCountDao
				.getInventoryCount(countDate, countDivision, countLocation,
					itemCd, lotNo, countUpdateDate);
		return bean;
	}

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return List<InquiryUpdateList>
	 */
	public List<InquiryUpdateList> getList(final Timestamp countDate,
			final String countDivision) {
		List<InquiryUpdateList> list = inquiryUpdateListDao.getList(countDate,
			countDivision, null, null, null);
		return list;
	}

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @param countLocation ロケーションコード
	 * @param itemCd 品目コード
	 * @param lotNo 入荷ロット番号/包装指図番号
	 * @return List<InquiryUpdateList>
	 */
	public List<InquiryUpdateList> getList(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo) {
		List<InquiryUpdateList> list = inquiryUpdateListDao.getList(countDate,
			countDivision, countLocation, itemCd, lotNo);
		return list;
	}

	/**
	 * 在庫更新
	 * @param frm InquiryUpdateForm
	 * @param tantoCd 担当者コード
	 */
	public void stockUpdate(final InquiryUpdateForm frm, final String tantoCd) {
		Stockinout stockinout = new Stockinout(zaiCtrlDao);

		/* 棚差調整 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 明細取得 */
			List<InquiryUpdateList> list = getList(frm.getSrhCountDate(), frm
					.getSearchList().get(i).getNameCd());

			for (int j = 0; j < list.size(); j++) {
				// BigDecimal qty = list.get(j).getInputQty().subtract(
				// list.get(j).getCountQty());
				// BigDecimal fraction =
				// list.get(j).getInputfraction().subtract(
				// list.get(j).getFractionQty());
				/* 変更元在庫数量計算 */
				ConvInventoryResult result = convInventoryLogic
						.packToInventory(list.get(j).getItemCd(), list.get(j)
								.getCountQty(), list.get(j).getFractionQty());

				BigDecimal qtyFrom = result.getInventoryQty();

				/* 変更後在庫数量計算 */
				result = convInventoryLogic.packToInventory(list.get(j)
						.getItemCd(), list.get(j).getInputQty(), list.get(j)
						.getInputfraction());

				/* 品目が未登録の場合は処理しない */
				if (!result.getCode().equals(new BigDecimal("1"))) {
					BigDecimal qtyTo = result.getInventoryQty();

					/* 差分計算 */
					BigDecimal qty = qtyTo.subtract(qtyFrom);

					/* 在庫数量計算 */
					// ConvInventoryResult result = convInventoryLogic
					// .packToInventory(list.get(j).getItemCd(), qty, fraction);
					/* 棚差調整入力処理 */
					if (!stockinout.adjustInventory(list.get(j).getItemCd(),
						qty, frm.getSrhUpdateDate(), list.get(j)
								.getCountLocation(), list.get(j).getLotNo(),
						tantoCd)) {
						throw new LogicExceptionEx();
					}
				}
			}
		}

		/* 処理日更新 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 明細取得 */
			List<InquiryUpdateList> list = getList(frm.getSrhCountDate(), frm
					.getSearchList().get(i).getNameCd());

			for (int j = 0; j < list.size(); j++) {
				/* 在庫更新処理日登録 */
				InventoryCount bean = inventoryCountDao.getEntity(list.get(j)
						.getCountDate(), list.get(j).getCountDivision(), list
						.get(j).getCountLocation(), list.get(j).getItemCd(),
					list.get(j).getLotNo());

				if (bean != null) {
					bean.setCountUpdateDate(bean.getCurrentTimestamp());

					try {
						/* 更新処理 */
						inventoryCountDao.update(bean);
					} catch (NotSingleRowUpdatedRuntimeException e) {
						/* 排他エラー */
						throw new OptimisticLockRuntimeException();
					}
				}
			}
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			final NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
	}

	/**
	 * inquiryInventoryCountDaoを設定します。
	 * @param inquiryInventoryCountDao inquiryInventoryCountDao
	 */
	public void setInquiryInventoryCountDao(
			final InquiryInventoryCountDao inquiryInventoryCountDao) {
		this.inquiryInventoryCountDao = inquiryInventoryCountDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param zaiCtrlDao zaiCtrlDao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * inquiryUpdateListDaoを設定します。
	 * @param inquiryUpdateListDao inquiryUpdateListDao
	 */
	public void setInquiryUpdateListDao(
			final InquiryUpdateListDao inquiryUpdateListDao) {
		this.inquiryUpdateListDao = inquiryUpdateListDao;
	}

	/**
	 * inventoryCountDaoを設定します。
	 * @param inventoryCountDao inventoryCountDao
	 */
	public void setInventoryCountDao(final InventoryCountDao inventoryCountDao) {
		this.inventoryCountDao = inventoryCountDao;
	}

	/**
	 * convInventoryLogicを設定します。
	 * @param convInventoryLogic convInventoryLogic
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}
}
