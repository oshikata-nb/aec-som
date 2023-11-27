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
 * 包装指図－製造指図フォーミュラDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionDirectionFormulaListDao {

	/** BEANアノテーション */
	Class<PkgDirectionDirectionFormulaList> BEAN = PkgDirectionDirectionFormulaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PkgDirectionDirectionFormulaList
	 * @return Insert件数
	 */
	int insert(PkgDirectionDirectionFormulaList bean);

	/**
	 * Update.
	 * @param bean PkgDirectionDirectionFormulaList
	 * @return Update件数
	 */
	int update(PkgDirectionDirectionFormulaList bean);

	/**
	 * Delete.
	 * @param bean PkgDirectionDirectionFormulaList
	 * @return Delete件数
	 */
	int delete(PkgDirectionDirectionFormulaList bean);

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
	String findByDirectionNoStepNo_QUERY
		= "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_TYPE = -1";
	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<RecipeInspection> データリスト
	 */
	List<PkgDirectionDirectionFormulaList> findByDirectionNoStepNo
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 配合一覧画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<PkgDirectionDirectionFormulaList> 検索結果リスト
	 */
	List<PkgDirectionDirectionFormulaList> getList
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 最終LINE_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return PkgDirectionDirectionFormulaList 最終LINE_NO
	 */
	PkgDirectionDirectionFormulaList getLastLineNo
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastSeq(). */
	String getLastSeq_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 最終SEQ_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return PkgDirectionDirectionFormulaList 最終SEQ_NO
	 */
	PkgDirectionDirectionFormulaList getLastSeq
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionDivision,directionNo,stepNo,lineNo";
	/**
	 * 配合詳細画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNo
	 * @param lineNo 行No
	 * @return PkgDirectionDirectionProcedureList 検索結果
	 */
	PkgDirectionDirectionFormulaList getEntity(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション getSearchFinishList(). */
	String getSearchFinishList_ARGS = "directionDivision,directionNo";
	/**
	 * 指図区分、指図番号、タイプ-1以外で検索
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionFormulaList> 検索結果
	 */
	List<PkgDirectionDirectionFormulaList> getSearchFinishList(BigDecimal directionDivision, String directionNo);

	/** QUERYアノテーション deleteFinishListByDirectionNo(). */
	String deleteFinishListByDirectionNo_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND LINE_TYPE != -1";
	/**
	 * 指図区分、指図番号、LINE_TYPE(-1以外)に紐づくデータをすべて削除
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteFinishListByDirectionNo(BigDecimal directionDivision, String directionNo);

	/** QUERYアノテーション getFinishList(). */
	String getFinishList_QUERY =
		"DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_NO > 1001 AND LINE_TYPE != -1";
	/**
	 * 指図区分、指図番号、STEP_NO、LINE_NO(1001より大)、LINE_TYPE(-1以外)のデータを取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return List<PkgDirectionDirectionFormulaList> 検索結果
	 */
	List<PkgDirectionDirectionFormulaList> getFinishList
		(BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

}
