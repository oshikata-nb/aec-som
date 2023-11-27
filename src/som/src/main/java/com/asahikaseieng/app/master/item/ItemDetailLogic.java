/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueue;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory.ItemCategoryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目基本ロジック interface.
 * @author t0011036
 */
public interface ItemDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @throws NoDataException NoDataException
	 * @return ItemQueue
	 */
	ItemQueue getEntity(final String itemCd, final BigDecimal version)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueDetail
	 */
	ItemQueueDetail getDetailEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemDetail
	 */
	ItemDetail getItemDetailEntity(final String itemCd, final BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getLastVersion(final String itemCd);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getLocationEntity(final String locationCd);

	/**
	 * 製造品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ProductAttributeQueue
	 */
	ProductAttributeQueue getProductEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 販売品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ArticleAttributeQueue
	 */
	ArticleAttributeQueue getArticleEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 購入品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueue
	 */
	PurchaseAttributeQueue getPurchaseEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 単位リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getUnitList();

	/**
	 * 品目分類リスト取得
	 * @return List<ItemCategoryListForComboboxes>
	 */
	List<ItemCategoryListForComboboxes> getItemCategoryList();
	
	/**
	 * 課税区分リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getTaxCategoryList();

	/**
	 * 承認登録処理を行う.
	 * @param bean 更新対象データ
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void approvalUpdate(final ItemQueue bean) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 否認・承認取消処理を行う.
	 * @param bean 更新対象データ
	 * @param activate true:有効品目
	 */
	void approvalCancelUpdate(final ItemQueue bean, final Boolean activate);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 * @param reason 更新理由
	 * @param insertFlg 新規フラグ
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	void insert(final ItemQueue bean, final String reason,
			final String insertFlg, final String copyFlg,
			final String copyItemCd, final BigDecimal copyVersion);

	/**
	 * 更新処理を行う.
	 * @param bean 追加対象データ
	 * @param reason 更新理由
	 */
	void update(final ItemQueue bean, final String reason);

	/**
	 * 削除処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @throws NoDataException NoDataException
	 */
	void delete(final String itemCd, final BigDecimal version)
			throws NoDataException;

	/**
	 * 更新処理を行う.
	 * @param bean 追加対象データ
	 */
	void itemQueueUpdate(final ItemQueue bean);

	/**
	 * 最新品目コード取得
	 * @return 最新品目コード
	 */
	String getNewItemCd();
}
