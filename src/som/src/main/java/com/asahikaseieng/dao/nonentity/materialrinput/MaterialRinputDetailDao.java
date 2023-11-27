/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;


/**
 * 外注原材料投入実績入力画面ヘッダ部用Daoインターフェース.
 *
 * @author tosco
 */
public interface MaterialRinputDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetail.class;

	/** ARGSアノテーション getHeader */
	String getHeader_ARGS = "buySubcontractOrderNo";

	/**
	 * 購買外注オーダ検索メソッド
	 * @param buySubcontractOrderNo 発注番号
	 * @return MaterialRinputDetail ヘッダ部データ
	 */
	MaterialRinputDetail getHeader(String buySubcontractOrderNo);

}
