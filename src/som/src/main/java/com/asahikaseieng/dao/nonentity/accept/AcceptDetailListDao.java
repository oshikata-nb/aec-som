/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;
import java.util.List;

/**
 * 受入入力 詳細画面用Daoインターフェース.
 * 
 * @author tosco
 */
public interface AcceptDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptDetailList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "purchaseNo,kbn";

	/**
	 * 購買データ取得.
	 * @param purchaseNo 購買NO
	 * @param kbn 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 *            ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @return AcceptDetailList 購買データ
	 */
	List<AcceptDetailList> getEntity(String purchaseNo, String kbn);

	/** ARGSアノテーション getSumAcceptQty(). */
	String getSumAcceptQty_ARGS = "buySubcontractOrderNo,slipNo";

	/**
	 * 受入数量の合計値取得.
	 * @param buySubcontractOrderNo 発注番号
	 * @param slipNo 仕入番号
	 * @return BigDecimal 受入数量の合計値
	 */
	BigDecimal getSumAcceptQty(String buySubcontractOrderNo, String slipNo);

	/** ARGSアノテーション getCountNotAccept(). */
	String getCountNotAccept_ARGS = "buySubcontractOrderNo";

	/**
	 * 未受入データ件数取得.
	 * @param buySubcontractOrderNo 発注番号
	 * @return BigDecimal 未受入データ件数
	 */
	BigDecimal getCountNotAccept(String buySubcontractOrderNo);

	/** ARGSアノテーション getDataTotalDivision(). */
	String getDataTotalDivision_ARGS = "categoryDivision";

	/**
	 * データ集計区分取得.
	 * @param categoryDivision 仕入区分(分類コード)
	 * @return BigDecimal ﾃﾞｰﾀ集計区分
	 */
	BigDecimal getDataTotalDivision(String categoryDivision);

	/** ARGSアノテーション getCountAvailableLocation(). */
	String getCountAvailableLocation_ARGS = "locationCd";

	/**
	 * 在庫可能ロケーション件数取得.
	 * @param locationCd ロケーションコード
	 * @return BigDecimal 在庫可能ロケーション件数
	 */
	int getCountAvailableLocation(String locationCd);

	/** ARGSアノテーション getPurchaseNo(). */
	String getPurchaseNo_ARGS = "buySubcontractOrderNo,orderDivideNo,rowNo";

	/**
	 * 新規登録時の購買No取得.
	 * @param buySubcontractOrderNo 発注番号
	 * @param orderDivideNo 発注番号分納枝番
	 * @param rowNo 行番号
	 * @return AcceptDetailList 購買データ
	 */
	AcceptDetailList getPurchaseNo(String buySubcontractOrderNo,
			String orderDivideNo, String rowNo);

	/** ARGSアノテーション getDataTotalDivisionSales(). */
	String getDataTotalDivisionSales_ARGS = "categoryDivision";

	/**
	 * データ集計区分取得.
	 * @param categoryDivision 売上区分(分類コード)
	 * @return BigDecimal ﾃﾞｰﾀ集計区分
	 */
	BigDecimal getDataTotalDivisionSales(String categoryDivision);
}
