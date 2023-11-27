/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 製造指図ヘッダーメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface RdirectionDirectionHeaderListDao {

	/** BEANアノテーション */
	Class<RdirectionDirectionHeaderList> BEAN = RdirectionDirectionHeaderList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Direction
	 * @return Insert件数
	 */
	int insert(RdirectionDirectionHeaderList bean);

	/**
	 * Update.
	 * @param bean Direction
	 * @return Update件数
	 */
	int update(RdirectionDirectionHeaderList bean);

	/**
	 * Delete.
	 * @param bean Direction
	 * @return Delete件数
	 */
	int delete(RdirectionDirectionHeaderList bean);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索
	 * @param condition 検索条件
	 * @return List<RdirectionDirectionHeaderList>
	 */
	List<RdirectionDirectionHeaderList> getSearchList(RdirectionDirectionHeaderListPagerCondition condition);

	/** ARGSアノテーション getSearchList(). */
	String findByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return RdirectionDirectionHeaderList
	 */
	RdirectionDirectionHeaderList findByDirectionNo(String directionNo);

	/** ARGSアノテーション getInspectionHeader */
	String getInspectionHeader_ARGS = "directionNo,stepNo";
	/**
	 * 検査詳細のヘッダーデータを主キーで製造指図ヘッダから取得
	 * @param directionNo 指図番号
	 * @param stepNo     STEP_NO
	 * @return RdirectionDirectionHeaderList
	 */
	RdirectionDirectionHeaderList getInspectionHeader(String directionNo, BigDecimal stepNo);
}
