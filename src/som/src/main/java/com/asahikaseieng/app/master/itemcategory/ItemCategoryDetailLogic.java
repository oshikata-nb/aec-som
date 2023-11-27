/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import com.asahikaseieng.dao.entity.master.itemcategory.ItemCategory;
import com.asahikaseieng.dao.nonentity.master.itemcategorydetail.ItemCategoryDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目分類詳細ロジック interface.
 * @author t0011036
 */
public interface ItemCategoryDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param itemCategory 品目分類コード
	 * @throws NoDataException NoDataException
	 * @return ItemCategory
	 */
	ItemCategory getEntity(final String itemCategory) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param itemCategory 品目分類コード
	 * @return ItemCategoryDetail
	 */
	ItemCategoryDetail getDetailEntity(final String itemCategory);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final ItemCategory bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final ItemCategory bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final ItemCategory bean) throws NoDataException;
}
