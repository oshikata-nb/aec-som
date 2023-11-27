/*
 * Created on 2008/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.unitprice;

import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;

/**
 * 有効単価取得処理 ロジッククラス interface.
 * @author t1344224
 */
public interface GetValidUnitpriceLogic {

	/**
	 * 帳合コードと品目コードから有効な有効単価を取得
	 * 
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @param date 日付
	 * @return VaridUnitprice 有効単価
	 */
	VaridUnitprice getValidUnitprice(String balanceCd, String itemCd,
			String date);
}
