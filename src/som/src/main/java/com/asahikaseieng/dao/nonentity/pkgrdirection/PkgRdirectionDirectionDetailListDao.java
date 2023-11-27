/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.util.List;

/**
 * 包装実績－製造指図明細Daoインターフェース.
 * @author kanri-user
 */
public interface PkgRdirectionDirectionDetailListDao {

	/** BEANアノテーション. */
	Class BEAN = PkgRdirectionDirectionDetailList.class;

	//
	// インスタンスメソッド
	//
	/** QUERYアノテーション getDivideOriginList(). */
	String getDivideOriginList_QUERY = "PKG_DIRECTION_NO = ?";
	/**
	 * 分納元データを取得する
	 * @param pkgDirectionNo 指図番号
	 * @return List<PkgRdirectionDirectionDetailList> 検索結果
	 */
	List<PkgRdirectionDirectionDetailList> getDivideOriginList(String pkgDirectionNo);

}
