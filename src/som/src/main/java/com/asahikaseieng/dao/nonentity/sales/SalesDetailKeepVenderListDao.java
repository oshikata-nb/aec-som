/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.util.List;

/**
 * 売上詳細(預り品)画面_得意先取得用用Daoインターフェース.
 *
 * @author tosco
 */
public interface SalesDetailKeepVenderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderList.class;

	/** ARGSアノテーション getSearchList */
	String getVenderList_ARGS = "balanceCd";

	/**
	 * 得意先取得メソッド
	 * @param balanceCd 帳合コード
	 * @return List<SalesDetailKeepVenderList> 得意先リスト
	 */
	List<SalesDetailKeepVenderList> getVenderList(final String balanceCd);

}
