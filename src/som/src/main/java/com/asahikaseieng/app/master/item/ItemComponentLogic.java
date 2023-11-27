/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueue;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目成分ロジック interface.
 * @author t0011036
 */
public interface ItemComponentLogic {
	/**
	 * 検索処理を行う.
	 * @param itemCd itemCd
	 * @param version version
	 * @return List<ComponentInformationQueueList>
	 */
	List<ComponentInformationQueueList> getList(final String itemCd,
			final BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @param indicateOrder indicateOrder
	 * @throws NoDataException NoDataException
	 * @return ComponentInformationQueue
	 */
	ComponentInformationQueue getEntity(final String itemCd,
			final BigDecimal version, final BigDecimal indicateOrder)
			throws NoDataException;

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
	 * @param componentCd 成分コード
	 * @return ComponentDetail
	 */
	ComponentDetail getComponentEntity(final String componentCd);

	/**
	 * ステータス更新処理を行う.
	 * @param bean 更新対象データ
	 */
	void updateStatus(final ComponentInformationQueue bean);

	/**
	 * 更新処理を行う.
	 * @param frm 更新対象データ
	 * @param tantoCd 担当者コード
	 */
	void regist(final ItemComponentForm frm, final String tantoCd);
}
