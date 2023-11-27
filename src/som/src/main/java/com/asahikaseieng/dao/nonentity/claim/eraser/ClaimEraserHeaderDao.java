/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;



/**
 * 消込入力詳細画面 消込ﾍｯﾀﾞｰ登録更新用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * 消込ﾍｯﾀﾞｰ登録処理
	 * 
	 * @param bean 消込ﾍｯﾀﾞｰ用Bean
	 * @return 登録件数
	 */
	int insert(ClaimEraserHeader bean);

	/**
	 * 消込ﾍｯﾀﾞｰ更新処理
	 * 
	 * @param bean 消込ﾍｯﾀﾞｰ用Bean
	 * @return 更新件数
	 */
	int update(ClaimEraserHeader bean);

	/**
	 * 消込ﾍｯﾀﾞｰ削除処理
	 * 
	 * @param bean 消込ﾍｯﾀﾞｰ用Bean
	 * @return 削除件数
	 */
	int delete(ClaimEraserHeader bean);

}
