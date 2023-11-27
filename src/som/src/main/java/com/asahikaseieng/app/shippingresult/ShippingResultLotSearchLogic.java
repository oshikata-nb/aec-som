/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.util.List;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロット検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface ShippingResultLotSearchLogic {

	/**
	 * 品目情報検索処理を行う.
	 * @param itemCd 品目コード
	 * @return Item 検索結果
	 * @throws NoDataException NoDataException
	 */
	Item getItem(final String itemCd) throws NoDataException;

	/**
	 * ロット検索処理を行う.
	 * @param condition 検索条件
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<ShippingResultLotSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<ShippingResultLotSearchList> getList(final ShippingResultLotSearchListPagerCondition condition,
		final String unitDivision, final String venderDivision, final String venderCd) throws NoDataException;
}
