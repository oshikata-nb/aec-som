/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.util.List;

/**
 * 帳票・ラベルマスタ用Daoインターフェース.
 *
 * @author tosco
 */
public interface MgrecipeLabelListDao {

	/** BEANアノテーション */
	Class<MgrecipeLabelList> BEAN = MgrecipeLabelList.class;

	//
	// インスタンスメソッド
	//
	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(MgrecipeLabelList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(MgrecipeLabelList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(MgrecipeLabelList bean);


	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "labelCd";

	/**
	 * エンティティ取得.
	 * @param labelCd labelCd
	 * @return Mgrecipelabel
	 */
	List<MgrecipeLabelList> getSearchList(String labelCd);

	/** ARGSアノテーション deleteByLabelId(). */
	String deleteByLabelId_ARGS = "labelCd";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param labelCd ラベルコード
	 * @return Delete件数
	 */
	int deleteByLabelId(String labelCd);

	/** ARGSアノテーション findByLabelCdCommonCd(). */
	String findByLabelCdCommonCd_ARGS = "labelCd,commonCd";
	/**
	 * キー検索．テーブル全項目取得
	 * @param labelCd ラベルコード
	 * @param commonCd 共通コード
	 * @return MgrecipeLabelList
	 */
	MgrecipeLabelList findByLabelCdCommonCd(String labelCd, String commonCd);

}
