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
import com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist.ItemRelatedGrecipeList;
import com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist.ItemRelatedMgrecipeList;

/**
 * 関連情報ロジック interface.
 * @author t0011036
 */
public interface ItemRelatedLogic {
	/**
	 * 原処方検索処理を行う.
	 * @param itemCd 品目コード
	 * @return List<ItemRelatedGrecipeList>
	 */
	List<ItemRelatedGrecipeList> getGrecipeList(final String itemCd);

	/**
	 * 基本処方検索処理を行う.
	 * @param itemCd 品目コード
	 * @return List<ItemRelatedMgrecipeList>
	 */
	List<ItemRelatedMgrecipeList> getMgrecipeList(final String itemCd);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);
}
