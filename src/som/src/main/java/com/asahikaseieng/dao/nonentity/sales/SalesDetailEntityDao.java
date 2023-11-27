/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.math.BigDecimal;



/**
 * 売上メンテ 売上詳細画面_売上トランザクション用Daoインターフェース.
 *
 * @author tosco
 */
public interface SalesDetailEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailEntity.class;

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
	String getSalesByVender_ARGS = "venderCd";
	/**
	 * 得意先関連情報を取得.
	 * @param venderCd 得意先コード
	 * @return SalesDetailNormalEntity
	 */
	SalesDetailEntity getSalesByVender(String venderCd);

	/** ARGSアノテーション getSalesForRegist(). */
	String getSalesForRegist_ARGS = "tantoCd,companyCd,deliveryCd,categoryDivision,venderCd,itemCd";
	/**
	 * 登録用売上トランザクションデータ取得.
	 * @param tantoCd          ログインユーザID
	 * @param companyCd        会社コード
	 * @param deliveryCd       納入先コード
	 * @param categoryDivision 分類コード
	 * @param venderCd         取引先コード
	 * @param itemCd           品目コード
	 * @return SalesDetailEntity 登録用売上トランザクションデータ
	 */
	SalesDetailEntity getSalesForRegist(String tantoCd, String companyCd, String deliveryCd,
			String categoryDivision, String venderCd, String itemCd);

	/**
	 * 会社マスタ検索.
	 * @return String 会社コード
	 */
	String getCompanyCd();

	/** ARGSアノテーション getDataTotalDivision(). */
	String getDataTotalDivision_ARGS = "categoryDivision";
	/**
	 * 売上区分よりデータ集計区分を取得.
	 * @param categoryDivision 売上区分
	 * @return String
	 */
	BigDecimal getDataTotalDivision(String categoryDivision);
}
