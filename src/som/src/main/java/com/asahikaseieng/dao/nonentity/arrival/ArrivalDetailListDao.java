/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.arrival;

import java.math.BigDecimal;
import java.util.List;

/**
 * 入荷入力 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ArrivalDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "purchaseNo,slipNo";

	/**
	 * 購買データ取得.
	 * @param purchaseNo 購買NO
	 * @param slipNo     仕入番号
	 * @return ArrivalDetailList 購買データ
	 */
	List<ArrivalDetailList> getEntity(String purchaseNo, String slipNo);

	/** ARGSアノテーション getSumArrivalQty(). */
	String getSumArrivalQty_ARGS = "buySubcontractOrderNo,slipNo";

	/**
	 * 予定入荷数量の合計値取得.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo     仕入番号
	 * @return ArrivalDetailList 購買データ
	 */
	BigDecimal getSumArrivalQty(String buySubcontractOrderNo, String slipNo);

}
