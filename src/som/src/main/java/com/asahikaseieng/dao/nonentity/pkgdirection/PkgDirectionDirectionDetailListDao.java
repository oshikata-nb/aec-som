/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.util.List;

/**
 * 包装指図－製造指図明細Daoインターフェース.
 * @author kanri-user
 */
public interface PkgDirectionDirectionDetailListDao {

	/** BEANアノテーション. */
	Class BEAN = PkgDirectionDirectionDetailList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "pkgDirectionNo";
	/**
	 * 包装指図番号に紐づくデータをすべて削除
	 * @param pkgDirectionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByPkgDirectionNo(String pkgDirectionNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "pkgDirectionNo";
	/**
	 * 包装指図番号に紐づくデータを取得
	 * @param pkgDirectionNo 指図番号
	 * @return List<PkgDirectionDirectionDetailList> 検索結果リスト
	 */
	List<PkgDirectionDirectionDetailList> getList(String pkgDirectionNo);
}
