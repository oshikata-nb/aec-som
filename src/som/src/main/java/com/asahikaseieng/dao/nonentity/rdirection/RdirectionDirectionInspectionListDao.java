/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.math.BigDecimal;
import java.util.List;


/**
 * 製造指図検査Daoインターフェース.
 *
 * @author tosco
 */
public interface RdirectionDirectionInspectionListDao {

	/** BEANアノテーション */
	Class<RdirectionDirectionInspectionList> BEAN = RdirectionDirectionInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RdirectionDirectionInspectionList
	 * @return Insert件数
	 */
	int insert(RdirectionDirectionInspectionList bean);

	/**
	 * Update.
	 * @param bean RdirectionDirectionInspectionList
	 * @return Update件数
	 */
	int update(RdirectionDirectionInspectionList bean);

	/**
	 * Delete.
	 * @param bean RdirectionDirectionInspectionList
	 * @return Delete件数
	 */
	int delete(RdirectionDirectionInspectionList bean);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo,stepNo";
	/**
	 * 指図番号、ステップNoで検索
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return List<RdirectionDirectionInspectionList>
	 */
	List<RdirectionDirectionInspectionList> getSearchList(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図区分、指図番号、STEP_NOから検査データを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return List<RdirectionDirectionInspectionList>
	 */
	List<RdirectionDirectionInspectionList> getList(
		BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionNo,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return RdirectionDirectionInspectionList 最終LINE_NO
	 */
	RdirectionDirectionInspectionList getLastLineNo(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNoStepNo_ARGS = "directionNo,stepNo";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて削除
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return Delete件数
	 */
	int deleteByDirectionNoStepNo(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionNo,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return RdirectionDirectionInspectionList
	 */
	RdirectionDirectionInspectionList getEntity(String directionNo, BigDecimal stepNo, BigDecimal lineNo);
}
