/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;

/**
 * 受入入力 売上トラン登録用データ取得Daoインターフェース.
 *
 * @author tosco
 */
public interface AcceptDetailSalesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptDetailSales.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSalesData(). */
	String getSalesData_ARGS = "tantoCd,companyCd,orderNo,rowNo";
	/**
	 * 購買データ取得.
	 * @param tantoCd   ログインユーザID
	 * @param companyCd 会社コード
	 * @param orderNo   受注番号
	 * @param rowNo     行番号(受注)
	 * @return AcceptDetailSales 売上トラン登録用データ
	 */
	AcceptDetailSales getSalesData(String tantoCd, String companyCd, String orderNo, BigDecimal rowNo);

	/**
	 * 会社マスタ検索.
	 * @return String 会社コード
	 */
	String getCompanyCd();

}
