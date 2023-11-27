/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shipping.ShippingList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingDetailListForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<ShippingList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<ShippingList> getSearchList(final ShippingListPagerCondition condition)
			throws NoDataException;

	/**
	 * 出荷指図一覧検索処理(詳細)
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingDetailListForReport> 検索結果リスト
	 * 
	 */
	List<ShippingDetailListForReport> getDetailList(
			final ShippingListConditionForReport condition);

	/**
	 * 出荷指図一覧検索処理(ヘッダ)
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingListForReport> 検索結果リスト
	 */
	List<ShippingListForReport> getHeaderList(
			final ShippingListConditionForReport condition);

	/**
	 * 取消確定処理を行う.
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	void cancelFix(final List<ShippingList> searchList, final String tantoCd)
			throws NoDataException, Exception;
}
