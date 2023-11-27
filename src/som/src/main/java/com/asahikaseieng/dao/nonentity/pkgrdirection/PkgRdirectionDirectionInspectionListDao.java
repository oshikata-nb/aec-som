/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装実績－検査Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgRdirectionDirectionInspectionListDao {

	/** BEANアノテーション */
	Class<PkgRdirectionDirectionInspectionList> BEAN = PkgRdirectionDirectionInspectionList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PkgRdirectionDirectionInspectionList
	 * @return Insert件数
	 */
	int insert(PkgRdirectionDirectionInspectionList bean);

	/**
	 * Update.
	 * @param bean PkgRdirectionDirectionInspectionList
	 * @return Update件数
	 */
	int update(PkgRdirectionDirectionInspectionList bean);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionDivision,directionNo";
	/**
	 * 包装実績番号に紐づくデータをすべて削除
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
	 * @param stepNo ステップNO
	 * @return List<PkgRdirectionDirectionInspectionList> データリスト
	 */
	List<PkgRdirectionDirectionInspectionList> findByDirectionNoStepNo
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図区分 指図番号、ステップNoで検索
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return List<PkgRdirectionDirectionInspectionList>
	 */
	List<PkgRdirectionDirectionInspectionList> getSearchList(
		BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return PkgRdirectionDirectionInspectionList 最終LINE_NO
	 */
	PkgRdirectionDirectionInspectionList getLastLineNo(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNoStepNo_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて削除
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return Delete件数
	 */
	int deleteByDirectionNoStepNo(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** QUERYアノテーション getDivideOriginList(). */
	String getDivideOriginList_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ?";
	/**
	 * 分納元データを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionDirectionInspectionList> 検索結果
	 */
	List<PkgRdirectionDirectionInspectionList> getDivideOriginList
		(BigDecimal directionDivision, String directionNo);

		/** ARGSアノテーション getInspecrionDetail(). */
	String getInspecrionDetail_ARGS = "directionDivision,directionNo,stepNo,lineNo";
	/**
	 * エンティティ取得.
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return PkgRdirectionDirectionInspectionList
	 */
	PkgRdirectionDirectionInspectionList getInspecrionDetail(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo, BigDecimal lineNo);

}
