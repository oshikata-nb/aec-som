/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.math.BigDecimal;
import java.util.List;


/**
 * 外注原材料投入実績入力画面明細部用Daoインターフェース.
 *
 * @author tosco
 */
public interface MaterialRinputDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailList.class;

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "buySubcontractOrderNo";

	/**
	 * 購買外注原材料投入実績検索メソッド
	 * @param buySubcontractOrderNo 発注番号
	 * @return List<MaterialRinputDetailList> 明細部データ
	 */
	List<MaterialRinputDetailList> getDetailList(String buySubcontractOrderNo);

	/** ARGSアノテーション deleteStockpdInfo(). */
	String deleteStockpdInfo_ARGS = "buySubcontractOrderNo,recipeId,stepNo,lineNo";

	/**
	 * 在庫引落情報を一括削除
	 * @param buySubcontractOrderNo 発注番号
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return Delete件数
	 */
	int deleteStockpdInfo(String buySubcontractOrderNo, BigDecimal recipeId, BigDecimal stepNo, BigDecimal lineNo);
}
