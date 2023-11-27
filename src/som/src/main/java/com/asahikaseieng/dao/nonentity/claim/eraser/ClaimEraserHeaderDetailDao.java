/*
 * Created on 2008/10/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;



/**
 * 消込入力詳細画面 消込ヘッダー内訳登録更新用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserHeaderDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeaderDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ERASER_NO";

	/**
	 * 消込ヘッダー内訳データ検索処理.
	 * @param eraserNo 消込番号
	 * @return List<EraserHeaderDetail> 消込ヘッダー内訳データ
	 */
	List<ClaimEraserHeaderDetail> getEntity(String eraserNo);

	/**
	 * 消込ヘッダー内訳登録処理
	 * 	 * @param bean 消込ヘッダー内訳用Bean
	 * @return 登録件数
	 */
	int insert(ClaimEraserHeaderDetail bean);

	/**
	 * 消込ヘッダー内訳削除処理
	 * 
	 * @param bean 消込ヘッダー内訳Bean
	 * @return 削除件数
	 */
	int delete(ClaimEraserHeaderDetail bean);

//	/** ARGSアノテーション delete(). */
//	String deleteDetail_ARGS = "bean";
//
//	/**
//	 * 消込ヘッダー内訳削除処理
//	 * //	 * @param bean 消込ヘッダー内訳Bean
//	 * @return 削除件数
//	 */
//	int deleteDetail(EraserHeaderDetail bean);

}
