/*
 * Created on 2008/10/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

/**
 * 消込入力詳細画面(下段)用Daoインターフェース.(カスタマイズ)
 * @author tosco
 */
public interface ClaimEraserSalesCsmDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales.class;

	//
	// インスタンスメソッド
	//

	/**
	 * 消込入力詳細 売上トランザクション更新処理 （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 売上トラン用Bean
	 * @return 更新件数
	 */
	int update(ClaimEraserSales bean);

}
