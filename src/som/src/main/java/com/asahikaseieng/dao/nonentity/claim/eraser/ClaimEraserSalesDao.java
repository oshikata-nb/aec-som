/*
 * Created on 2008/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;


/**
 * 消込入力詳細画面(下段)用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserSalesDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSalesData(). */
	String getSalesData_ARGS = "eraserNo, sectionCd, venderCd, tantoCd, tantoNm";

	/**
	 * 消込入力詳細(下段) 請求データ検索処理.
	 * @param eraserNo 消込番号
	 * @param sectionCd 部門コード
	 * @param venderCd  請求先コード
	 * @param tantoCd 	 担当者コード
	 * @param tantoNm 	 担当者名称
	 * @return List<EraserSales> 消込入力詳細(下段) 請求データ
	 */
	List<ClaimEraserSales> getSalesData(String eraserNo
								, String sectionCd
								, String venderCd
								, String tantoCd
								, String tantoNm);

	/**
	 * 消込入力詳細 売上トランザクション更新処理
	 * （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 売上トラン用Bean
	 * @return 更新件数
	 */
	int update(ClaimEraserSales bean);

}
