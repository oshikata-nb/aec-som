/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.math.BigDecimal;
import java.util.List;

/**
 * 製造指図検査Dao インターフェース.
 *
 * @author tosco
 */
public interface DirectionDirectionInspectionListDao {

	/** BEANアノテーション */
	Class<DirectionDirectionInspectionList> BEAN = DirectionDirectionInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DirectionDirectionInspectionList
	 * @return Insert件数
	 */
	int insert(DirectionDirectionInspectionList bean);

	/**
	 * Update.
	 * @param bean DirectionDirectionInspectionList
	 * @return Update件数
	 */
	int update(DirectionDirectionInspectionList bean);

	/**
	 * Delete.
	 * @param bean DirectionDirectionInspectionList
	 * @return Delete件数
	 */
	int delete(DirectionDirectionInspectionList bean);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図区分、指図番号、STEP_NOから検査データを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return List<DirectionDirectionInspectionList>
	 */
	List<DirectionDirectionInspectionList> getList(
		BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionNo,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return DirectionDirectionInspectionList
	 */
	DirectionDirectionInspectionList getEntity(String directionNo, BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo,stepNo";
	/**
	 * 指図番号、ステップNoで検索
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<DirectionDirectionInspectionList>
	 */
	List<DirectionDirectionInspectionList> getSearchList(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionNo,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return DirectionDirectionInspectionList 最終LINE_NO
	 */
	DirectionDirectionInspectionList getLastLineNo(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNoStepNo_ARGS = "directionNo,stepNo";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて削除
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return Delete件数
	 */
	int deleteByDirectionNoStepNo(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図区分、指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);
}
