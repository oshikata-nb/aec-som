/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.changehistorylist.ChangeHistoryList;
import com.asahikaseieng.dao.nonentity.master.changehistorylist.ChangeHistoryListDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;

/**
 * 品目更新履歴ロジック 実装クラス.
 * @author t0011036
 */
public class ItemHistoryLogicImpl implements ItemHistoryLogic {

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private ChangeHistoryListDao changeHistoryListDao;

	/**
	 * コンストラクタ.
	 */
	public ItemHistoryLogicImpl() {
	}

	/**
	 * 更新履歴検索
	 * @param menuId メニュー番号
	 * @param itemCd 品目コード
	 * @return List<ChangeHistoryList>
	 */
	public List<ChangeHistoryList> getList(final BigDecimal menuId,
			final String itemCd) {
		List<ChangeHistoryList> bean = changeHistoryListDao.getList(menuId,
			itemCd);
		return bean;
	}

	/**
	 * 品目検索（ヘッダー用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	public ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version) {
		ItemQueueHeader bean = itemQueueHeaderDao.getEntity(itemCd, version);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemQueueHeaderDaoを設定します。
	 * @param itemQueueHeaderDao itemQueueHeaderDao
	 */
	public void setItemQueueHeaderDao(
			final ItemQueueHeaderDao itemQueueHeaderDao) {
		this.itemQueueHeaderDao = itemQueueHeaderDao;
	}

	/**
	 * changeHistoryListDaoを設定します。
	 * @param changeHistoryListDao changeHistoryListDao
	 */
	public void setChangeHistoryListDao(
			final ChangeHistoryListDao changeHistoryListDao) {
		this.changeHistoryListDao = changeHistoryListDao;
	}
}
