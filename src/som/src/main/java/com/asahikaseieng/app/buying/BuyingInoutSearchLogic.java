/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buying;

import java.util.List;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchList;
import com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払検索(ポップアップ)ロジック interface.
 * @author t1344224
 */
public interface BuyingInoutSearchLogic {

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
	 * @return List<BuyingInoutSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<BuyingInoutSearchList> getList(
			final BuyingInoutSearchListPagerCondition condition,
			final String venderCd, final String unitOfOperationManagement)
			throws NoDataException;

}
