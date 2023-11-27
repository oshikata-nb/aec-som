/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.math.BigDecimal;
import java.util.List;



/**
 * 売上詳細(預り品)画面_出庫ロケーションDaoインターフェース.
 *
 * @author tosco
 */
public interface SalesDetailKeepLocationListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepLocationList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "salesNo,inoutDivision";
	/**
	 * 出庫ロケーション検索メソッド
	 * 
	 * @param salesNo 売上番号
	 * @param inoutDivision 受払区分
	 * @return List<SalesDetailKeepLocationList> 検索結果一覧
	 */
	List<SalesDetailKeepLocationList> getSearchList(final String salesNo, final BigDecimal inoutDivision);
}
