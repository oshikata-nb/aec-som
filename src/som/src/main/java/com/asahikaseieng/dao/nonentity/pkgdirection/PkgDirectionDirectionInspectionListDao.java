/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装指図－検査Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionDirectionInspectionListDao {

	/** BEANアノテーション */
	Class<PkgDirectionDirectionInspectionList> BEAN = PkgDirectionDirectionInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PkgDirectionDirectionInspectionList
	 * @return Insert件数
	 */
	int insert(PkgDirectionDirectionInspectionList bean);

	/**
	 * Update.
	 * @param bean PkgDirectionDirectionInspectionList
	 * @return Update件数
	 */
	int update(PkgDirectionDirectionInspectionList bean);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionDivision,directionNo";
	/**
	 * 包装指図番号に紐づくデータをすべて削除
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(BigDecimal directionDivision, String directionNo);

	/** QUERYアノテーション findByDirectionNoStepNo(). */
	String findByDirectionNoStepNo_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO = ?";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<PkgDirectionDirectionInspectionList> データリスト
	 */
	List<PkgDirectionDirectionInspectionList> findByDirectionNoStepNo
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図区分 指図番号、ステップNoで検索
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<PkgDirectionDirectionInspectionList>
	 */
	List<PkgDirectionDirectionInspectionList> getSearchList(
		BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return PkgDirectionDirectionInspectionList 最終LINE_NO
	 */
	PkgDirectionDirectionInspectionList getLastLineNo(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNoStepNo_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて削除
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return Delete件数
	 */
	int deleteByDirectionNoStepNo(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getInspecrionDetail(). */
	String getInspecrionDetail_ARGS = "directionDivision,directionNo,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return PkgDirectionDirectionInspectionList
	 */
	PkgDirectionDirectionInspectionList getInspecrionDetail(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo, BigDecimal lineNo);

}
