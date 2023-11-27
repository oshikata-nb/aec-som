/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.itemcategory.ItemCategory;
import com.asahikaseieng.dao.entity.master.itemcategory.ItemCategoryDao;
import com.asahikaseieng.dao.nonentity.master.itemcategorydetail.ItemCategoryDetail;
import com.asahikaseieng.dao.nonentity.master.itemcategorydetail.ItemCategoryDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 品目分類詳細ロジック 実装クラス.
 * @author t0011036
 */
public class ItemCategoryDetailLogicImpl implements ItemCategoryDetailLogic {

	private ItemCategoryDao itemCategoryDao;

	private ItemCategoryDetailDao itemCategoryDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryDetailLogicImpl() {
	}

	/**
	 * 品目分類検索（登録用）
	 * @param itemCategory 品目分類コード
	 * @return ItemCategory
	 * @throws NoDataException NoDataException
	 */
	public ItemCategory getEntity(final String itemCategory)
			throws NoDataException {
		if (StringUtils.isEmpty(itemCategory)) {
			throw new IllegalArgumentException("itemCategory is empty");
		}

		ItemCategory bean = itemCategoryDao.getEntity(itemCategory);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 品目分類検索（詳細用）
	 * @param itemCategory 品目分類コード
	 * @return ItemCategoryDetail
	 */
	public ItemCategoryDetail getDetailEntity(final String itemCategory) {
		ItemCategoryDetail bean = itemCategoryDetailDao.getEntity(itemCategory);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final ItemCategory bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			itemCategoryDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final ItemCategory bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			itemCategoryDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final ItemCategory bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			itemCategoryDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemCategoryDaoを設定します。
	 * @param itemCategoryDao itemCategoryDao
	 */
	public void setItemCategoryDao(final ItemCategoryDao itemCategoryDao) {
		this.itemCategoryDao = itemCategoryDao;
	}

	/**
	 * itemCategoryDetailDaoを設定します。
	 * @param itemCategoryDetailDao itemCategoryDetailDao
	 */
	public void setItemCategoryDetailDao(
			final ItemCategoryDetailDao itemCategoryDetailDao) {
		this.itemCategoryDetailDao = itemCategoryDetailDao;
	}
}
