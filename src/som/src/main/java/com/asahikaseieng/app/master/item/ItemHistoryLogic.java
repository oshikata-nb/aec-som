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
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;

/**
 * 品目更新履歴ロジック interface.
 * @author t0011036
 */
public interface ItemHistoryLogic {
	/**
	 * 更新履歴検索処理を行う.
	 * @param menuId メニュー番号
	 * @param itemCd 品目コード
	 * @return List<ChangeHistoryList>
	 */
	List<ChangeHistoryList> getList(final BigDecimal menuId, final String itemCd);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);
}
