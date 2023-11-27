/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchaseorder;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderList;
import com.asahikaseieng.dao.nonentity.purchaseorder.PurchaseOrderPagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListConditionForRepor;
import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListForRepor;
import com.asahikaseieng.exception.NoDataException;

/**
 * 発注書一覧 ロジッククラス interface.
 * @author tosco
 */
public interface PurchaseOrderListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<PurchaseOrderList> 発注書一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PurchaseOrderList> getSearchList(
			final PurchaseOrderPagerCondition condition) throws NoDataException;

	/**
	 * 更新処理(発注書ボタン押下時)
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 * @param organizationCd 部署コード
	 * @return String[] 発注書No
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	String[] updata(final List<PurchaseOrderList> searchList,
			final String tantoCd, final String organizationCd)
			throws NoDataException, Exception;

	/**
	 * 帳票Excel用一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseOrderListForRepor>発注書一覧 検索結果リスト
	 */
	List<PurchaseOrderListForRepor> getReportList(
			final PurchaseOrderListConditionForRepor condition);

	/**
	 * 
	 * ##ここにメソッドの説明を書いてください##
	 * @param searchList .
	 * @return errors .
	 */
	ActionMessages checkForOrder(final List<PurchaseOrderList> searchList);
}
