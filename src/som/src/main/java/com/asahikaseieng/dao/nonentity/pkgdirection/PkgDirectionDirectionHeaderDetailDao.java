/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

/**
 * 包装指図－製造指図ヘッダー情報Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionDirectionHeaderDetailDao {

	/** BEANアノテーション */
	Class<PkgDirectionDirectionHeaderDetail> BEAN = PkgDirectionDirectionHeaderDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionDivision,pkgDirectionNo";
	/**
	 * 包装指図番号で検索
	 * @param directionDivision 指図区分
	 * @param pkgDirectionNo 包装指図番号
	 * @return PkgDirectionHeaderDetail
	 */
	PkgDirectionDirectionHeaderDetail getEntity(BigDecimal directionDivision, String pkgDirectionNo);
}
