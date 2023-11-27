/*
 * Created on 2008/02/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.inventorycount.InventoryCount;
import com.asahikaseieng.dao.entity.inventorycount.InventoryCountDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputList;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputListDao;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputListPagerCondition;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListConditionForReport;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReportDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 棚卸入力ロジック 実装クラス.
 * @author tanaka
 */
public class InquiryInputListLogicImpl implements InquiryInputListLogic {

	private InquiryInputListDao inquiryInputListDao;

	private InquiryInputListForReportDao inquiryInputListForReportDao;

	private InventoryCountDao inventoryCountDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private ItemDetailDao itemDetailDao;

	private LocationDetailDao locationDetailDao;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListLogicImpl() {
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
	 * 棚卸一覧検索
	 * @param condition 検索条件
	 * @return List<InquiryInputList>
	 * @throws NoDataException NoDataException
	 */
	public List<InquiryInputList> getList(
			final InquiryInputListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InquiryInputList> list = inquiryInputListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final InquiryInputListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 棚卸一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InquiryInputListForReport>
	 */
	public List<InquiryInputListForReport> getListForReport(
			final InquiryInputListConditionForReport condition) {
		List<InquiryInputListForReport> list = inquiryInputListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 更新処理
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void update(final InquiryInputListForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		InventoryCount bean = new InventoryCount();

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			try {
				bean = inventoryCountDao.getEntity(frm.getSearchList().get(i)
						.getCountDate(), frm.getSearchList().get(i)
						.getCountDivision(), frm.getSearchList().get(i)
						.getCountLocation(), frm.getSearchList().get(i)
						.getItemCd(), frm.getSearchList().get(i).getLotNo());

				bean.setInputQty(frm.getSearchList().get(i).getInputQty());
				bean.setInputfraction(frm.getSearchList().get(i)
						.getInputfraction());
				bean.setUpdatorCd(tantoCd);

				/* 更新処理 */
				inventoryCountDao.update(bean);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		}
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
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	public LocationDetail getLocationEntity(final String locationCd) {
		LocationDetail bean = locationDetailDao.getEntity(locationCd);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryCountDaoを設定します。
	 * @param inventoryCountDao inventoryCountDao
	 */
	public void setInventoryCountDao(final InventoryCountDao inventoryCountDao) {
		this.inventoryCountDao = inventoryCountDao;
	}

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			final NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
	}

	/**
	 * inquiryInputListDaoを設定します。
	 * @param inquiryInputListDao inquiryInputListDao
	 */
	public void setInquiryInputListDao(
			final InquiryInputListDao inquiryInputListDao) {
		this.inquiryInputListDao = inquiryInputListDao;
	}

	/**
	 * inquiryInputListForReportDaoを設定します。
	 * @param inquiryInputListForReportDao inquiryInputListForReportDao
	 */
	public void setInquiryInputListForReportDao(
			final InquiryInputListForReportDao inquiryInputListForReportDao) {
		this.inquiryInputListForReportDao = inquiryInputListForReportDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}

	/**
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao locationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}
}
