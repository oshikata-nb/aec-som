/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;
import java.util.List;

/**
 * 受入・仕入　仕入入力画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface AcceptBuyingDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "slipNo,companyCd";
	/**
	 * 購買データ取得.
	 * @param slipNo     仕入番号
	 * @param companyCd  会社コード
	 * @return AcceptDetailList 購買データ
	 */
	List<AcceptBuyingDetailList> getEntity(String slipNo, String companyCd);

	/** ARGSアノテーション getCountAccounts */
	String getCountAccounts_ARGS = "accountsCd";
	/**
	 * 科目コードのマスター存在チェック
	 * @param accountsCd 科目コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	int getCountAccounts(final String accountsCd);

	/** ARGSアノテーション getDataTotalDivision(). */
	String getDataTotalDivision_ARGS = "categoryDivision";
	/**
	 * データ集計区分取得.
	 * @param categoryDivision 仕入区分(分類コード)
	 * @return BigDecimal ﾃﾞｰﾀ集計区分
	 */
	BigDecimal getDataTotalDivision(String categoryDivision);

	/** ARGSアノテーション getHousingUnitprice(). */
	String getHousingUnitprice_ARGS = "venderCd,itemCd,sumStockingQuantity";
	/**
	 * データ集計区分取得.
	 * @param venderCd 他社コード
	 * @param itemCd 品目コード
	 * @param sumStockingQuantity 仕入れ数量合計
	 * @return BigDecimal ﾃﾞｰﾀ集計区分
	 */
	AcceptBuyingDetailList getHousingUnitprice(String venderCd, String itemCd
			, BigDecimal sumStockingQuantity);
}
