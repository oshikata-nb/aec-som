/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;



/**
 * 消込入力詳細画面 消込トラン登録更新用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraser.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ERASER_NO";

	/**
	 * 消込入力詳細データ検索処理.
	 * @param eraserNo 消込番号
	 * @return List<Eraser> 消込入力詳細データ
	 */
	List<ClaimEraser> getEntity(String eraserNo);

	/**
	 * 消込トラン登録処理
	 * 	 * @param bean 消込トラン用Bean
	 * @return 登録件数
	 */
	int insert(ClaimEraser bean);

	/** ARGSアノテーション delete(). */
	String delete_ARGS = "eraserNo";

	/**
	 * 消込トラン削除処理
	 * 	 * @param eraserNo 消込番号
	 * @return 削除件数
	 */
	int delete(String eraserNo);

	/** PERSISTENT_PROPSアノテーション updateEraserAmount(). */
	String updateEraserAmount_PERSISTENT_PROPS = "eraserAmount";

	/**
	 * 消込トラン更新処理
	 * 
	 * @param bean 消込トランデータ
	 * @return 更新件数
	 */
	int updateEraserAmount(ClaimEraser bean);

}
