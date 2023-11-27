/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist.ItemRelatedGrecipeList;
import com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist.ItemRelatedGrecipeListDao;
import com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist.ItemRelatedMgrecipeList;
import com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist.ItemRelatedMgrecipeListDao;

/**
 * 関連情報ロジック 実装クラス.
 * @author t0011036
 */
public class ItemRelatedLogicImpl implements ItemRelatedLogic {

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private ItemRelatedGrecipeListDao itemRelatedGrecipeListDao;

	private ItemRelatedMgrecipeListDao itemRelatedMgrecipeListDao;

	/**
	 * コンストラクタ.
	 */
	public ItemRelatedLogicImpl() {
	}

	/**
	 * 原処方検索
	 * @param itemCd 品目コード
	 * @return List<ItemRelatedGrecipeList>
	 */
	public List<ItemRelatedGrecipeList> getGrecipeList(final String itemCd) {
		List<ItemRelatedGrecipeList> bean = itemRelatedGrecipeListDao
				.getList(itemCd);
		return bean;
	}

	/**
	 * 基本処方検索
	 * @param itemCd 品目コード
	 * @return List<ItemRelatedMgrecipeList>
	 */
	public List<ItemRelatedMgrecipeList> getMgrecipeList(final String itemCd) {
		List<ItemRelatedMgrecipeList> bean = itemRelatedMgrecipeListDao
				.getList(itemCd);
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
	 * itemRelatedGrecipeListDaoを設定します。
	 * @param itemRelatedGrecipeListDao itemRelatedGrecipeListDao
	 */
	public void setItemRelatedGrecipeListDao(
			final ItemRelatedGrecipeListDao itemRelatedGrecipeListDao) {
		this.itemRelatedGrecipeListDao = itemRelatedGrecipeListDao;
	}

	/**
	 * itemRelatedMgrecipeListDaoを設定します。
	 * @param itemRelatedMgrecipeListDao itemRelatedMgrecipeListDao
	 */
	public void setItemRelatedMgrecipeListDao(
			final ItemRelatedMgrecipeListDao itemRelatedMgrecipeListDao) {
		this.itemRelatedMgrecipeListDao = itemRelatedMgrecipeListDao;
	}
}
