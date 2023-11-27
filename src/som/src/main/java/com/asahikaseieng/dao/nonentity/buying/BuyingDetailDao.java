/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buying;

import java.math.BigDecimal;

/**
 * 仕入詳細画面用Daoインターフェース.
 * 
 * @author tosco
 */
public interface BuyingDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buying.BuyingDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getCountAccounts */
	String getCountAccounts_ARGS = "accountsCd";

	/**
	 * 科目コードのマスター存在チェック
	 * @param accountsCd 科目コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	int getCountAccounts(final String accountsCd);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "slipNo,companyCd";

	/**
	 * エンティティ取得.
	 * @param slipNo 仕入番号
	 * @param companyCd 会社コード
	 * @return Buying 検索結果
	 */
	BuyingDetail getEntity(String slipNo, String companyCd);

	/** ARGSアノテーション getEntity(). */
	String getDataTotalDivision_ARGS = "purchaseNo";

	/**
	 * データ集計区分取得.
	 * @param categoryDivision 仕入区分
	 * @return Buying 検索結果
	 */
	String getDataTotalDivision(String categoryDivision);

	/** ARGSアノテーション getCountUnitprice */
	String getCountUnitprice_ARGS = "itemCd,venderCd";

	/**
	 * 仕入先別単価マスタ存在チェック
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	int getCountUnitprice(final String itemCd, final String venderCd);

	/** ARGSアノテーション getItemRelatedData */
	String getItemRelatedData_ARGS = "itemCd";

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return BuyingDetail
	 */
	BuyingDetail getItemRelatedData(final String itemCd);

	/** ARGSアノテーション getVenderRelatedData */
	String getVenderRelatedData_ARGS = "itemCd,venderCd";

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail
	 */
	BuyingDetail getVenderRelatedData(final String itemCd, final String venderCd);

	/** ARGSアノテーション getStockingQuantityRelatedData */
	String getStockingQuantityRelatedData_ARGS = "itemCd,venderCd,stockingQuantity";

	/**
	 * 購入数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param stockingQuantity 購入数量
	 * @return BuyingDetail
	 */
	BuyingDetail getStockingQuantityRelatedData(final String itemCd,
			final String venderCd, final BigDecimal stockingQuantity);

	/** ARGSアノテーション getTaxRelatedData */
	String getTaxRelatedData_ARGS = "companyCd,itemCd,venderCd";

	/**
	 * 消費税関連データを取得する.
	 * @param companyCd 会社コード
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return BuyingDetail 消費税関連データ(消費税課税区分,消費税率,取引先マスタ.算出区分,自社マスタ.消費税算出区分)
	 */
	BuyingDetail getTaxRelatedData(final String companyCd, final String itemCd,
			final String venderCd);

	/** ARGSアノテーション getTaxRelatedData */
	String getTaxData_ARGS = "companyCd,itemCd";

	/**
	 * 消費税関連データを取得する.
	 * @param companyCd 会社コード
	 * @param itemCd 品目コード
	 * @return BuyingDetail 消費税関連データ(消費税課税区分,消費税率,取引先マスタ.算出区分,自社マスタ.消費税算出区分)
	 */
	BuyingDetail getTaxData(final String companyCd, final String itemCd);

	/**
	 * 会社マスタ検索.
	 * @return String 会社コード
	 */
	String getCompanyCd();
}
