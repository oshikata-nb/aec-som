/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mypage.shortcutset;

import java.util.List;


/**
 * 
 * ショートカット設定
 * @author tosco
 */
public interface ShortCutSetDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.mypage.shortcutset.ShortCutSet.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean bean
	 * @return Insert件数
	 */
	int insert(ShortCutSet bean);

	/**
	 * Delete.
	 * @param bean bean
	 * @return Delete件数
	 */
	int delete(ShortCutSet bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param tantoCd 担当者コード
	 * @return ShortCutSet
	 */
	List<ShortCutSet> getEntity(String tantoCd);

}
