/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.inventorycountdelete.InventoryCountDeleteDao;
import com.asahikaseieng.dao.nonentity.inventorycountdetail.InventoryCountDetail;
import com.asahikaseieng.dao.nonentity.inventorycountdetail.InventoryCountDetailDao;

/**
 * 棚卸準備キャンセル処理ロジック 実装クラス.
 * @author tanaka
 */
public class InquiryPreparationCancelLogicImpl implements
		InquiryPreparationCancelLogic {

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private InventoryCountDeleteDao inventoryCountDeleteDao;

	private InventoryCountDetailDao inventoryCountDetailDao;

	/**
	 * コンストラクタ
	 */
	public InquiryPreparationCancelLogicImpl() {
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
	 * 一括削除処理
	 * @param frm 削除データ
	 * @return 削除件数
	 */
	public int deleteInventoryCountList(final InquiryPreparationCancelForm frm) {
		int cnt = 0;

		for (int i = frm.getSearchList().size() - 1; i >= 0; i--) {
			cnt += inventoryCountDeleteDao.deleteList(frm.getSrhCountDate(),
				frm.getSearchList().get(i).getNameCd());
		}

		return cnt;
	}

	/**
	 * 棚卸検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return InventoryCountDetail
	 */
	public InventoryCountDetail getEntity(final java.sql.Timestamp countDate,
			final String countDivision) {
		InventoryCountDetail bean = inventoryCountDetailDao.getEntity(
			countDate, countDivision, countDate);
		return bean;
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
	 * inventoryCountDeleteDaoを設定します。
	 * @param inventoryCountDeleteDao inventoryCountDeleteDao
	 */
	public void setInventoryCountDeleteDao(
			final InventoryCountDeleteDao inventoryCountDeleteDao) {
		this.inventoryCountDeleteDao = inventoryCountDeleteDao;
	}

	/**
	 * inventoryCountDetailDaoを設定します。
	 * @param inventoryCountDetailDao inventoryCountDetailDao
	 */
	public void setInventoryCountDetailDao(
			final InventoryCountDetailDao inventoryCountDetailDao) {
		this.inventoryCountDetailDao = inventoryCountDetailDao;
	}
}
