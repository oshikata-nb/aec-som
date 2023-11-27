/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.itemremark.ItemRemark;
import com.asahikaseieng.dao.entity.master.itemremark.ItemRemarkDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.dao.nonentity.master.itemremarkdetail.ItemRemarkDetail;
import com.asahikaseieng.dao.nonentity.master.itemremarkdetail.ItemRemarkDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * その他ロジック 実装クラス.
 * @author t0011036
 */
public class ItemOtherLogicImpl implements ItemOtherLogic {

	private ItemRemarkDao itemRemarkDao;

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private ItemRemarkDetailDao itemRemarkDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ItemOtherLogicImpl() {
	}

	/**
	 * その他検索（登録用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemRemark
	 * @throws NoDataException NoDataException
	 */
	public ItemRemark getEntity(final String itemCd, final BigDecimal version)
			throws NoDataException {
		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		ItemRemark bean = itemRemarkDao.getEntity(itemCd, version);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * その他検索（詳細用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemRemarkDetail
	 */
	public ItemRemarkDetail getRemarkEntity(final String itemCd,
			final BigDecimal version) {
		ItemRemarkDetail bean = itemRemarkDetailDao.getEntity(itemCd, version);
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

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final ItemRemark bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			itemRemarkDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final ItemRemark bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			itemRemarkDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
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
	 * itemRemarkDaoを設定します。
	 * @param itemRemarkDao itemRemarkDao
	 */
	public void setItemRemarkDao(final ItemRemarkDao itemRemarkDao) {
		this.itemRemarkDao = itemRemarkDao;
	}

	/**
	 * itemRemarkDetailDaoを設定します。
	 * @param itemRemarkDetailDao itemRemarkDetailDao
	 */
	public void setItemRemarkDetailDao(
			final ItemRemarkDetailDao itemRemarkDetailDao) {
		this.itemRemarkDetailDao = itemRemarkDetailDao;
	}
}
