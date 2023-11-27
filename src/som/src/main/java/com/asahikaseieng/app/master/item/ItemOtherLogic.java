/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.itemremark.ItemRemark;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemremarkdetail.ItemRemarkDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * その他ロジック interface.
 * @author t0011036
 */
public interface ItemOtherLogic {
	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @throws NoDataException NoDataException
	 * @return ItemRemark
	 */
	ItemRemark getEntity(final String itemCd, final BigDecimal version)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemRemarkDetail
	 */
	ItemRemarkDetail getRemarkEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final ItemRemark bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final ItemRemark bean);
}
