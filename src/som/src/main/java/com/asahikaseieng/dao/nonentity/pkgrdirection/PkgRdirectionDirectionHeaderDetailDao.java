/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;

/**
 * 包装実績－製造指図ヘッダー情報Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgRdirectionDirectionHeaderDetailDao {

	/** BEANアノテーション */
	Class<PkgRdirectionDirectionHeaderDetail> BEAN = PkgRdirectionDirectionHeaderDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionDivision,pkgDirectionNo";
	/**
	 * 包装指図番号で検索
	 * @param directionDivision 指図区分
	 * @param pkgDirectionNo 包装指図番号
	 * @return PkgRdirectionDirectionHeaderDetail
	 */
	PkgRdirectionDirectionHeaderDetail getEntity(BigDecimal directionDivision, String pkgDirectionNo);
}
