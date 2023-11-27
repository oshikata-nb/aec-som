/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchList;
import com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払検索(ポップアップ)ロジック interface.
 * @author t1344224
 */
public interface SalesInoutSearchLogic {

	/**
	 * 品目データを取得する
	 * @param itemCd 品目コード
	 * @return ITEM 品目マスタデータ
	 */
	Item getItemData(final String itemCd);

	/**
	 * 受払検索処理を行う.
	 * @param condition 検索条件
	 * @param venderCd 仕入先コード
	 * @param unitOfOperationManagement 運用管理単位
	 * @return List<SalesInoutSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<SalesInoutSearchList> getList(
			final SalesInoutSearchListPagerCondition condition,
			final String venderCd, final String unitOfOperationManagement)
			throws NoDataException;

}
