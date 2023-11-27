/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.util.List;

/**
 * 帳票・ラベルマスタ用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeLabelListDao {

	/** BEANアノテーション */
	Class<GrecipeLabelList> BEAN = GrecipeLabelList.class;

	//
	// インスタンスメソッド
	//
	/**
	 * Insert.
	 * @param bean Mgrecipe
	 * @return Insert件数
	 */
	int insert(GrecipeLabelList bean);

	/**
	 * Update.
	 * @param bean Mgrecipe
	 * @return Update件数
	 */
	int update(GrecipeLabelList bean);

	/**
	 * Delete.
	 * @param bean Mgrecipe
	 * @return Delete件数
	 */
	int delete(GrecipeLabelList bean);


	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "labelCd";

	/**
	 * エンティティ取得.
	 * @param labelCd labelCd
	 * @return Mgrecipelabel
	 */
	List<GrecipeLabelList> getSearchList(String labelCd);

	/** ARGSアノテーション deleteByLabelId(). */
	String deleteByLabelId_ARGS = "labelCd";
	/**
	 * レシピインデックスに紐づくデータをすべて削除
	 * @param labelCd ラベルコード
	 * @return Delete件数
	 */
	int deleteByLabelId(String labelCd);

}
