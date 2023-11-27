/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

/**
 * 包装実績－製造指図フォーミュラDaoインターフェース.
 * 
 * @author tosco
 */
public interface PkgRdirectionDirectionFormulaListDao {

	/** BEANアノテーション */
	Class<PkgRdirectionDirectionFormulaList> BEAN = PkgRdirectionDirectionFormulaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionformula
	 * @return Insert件数
	 */
	int insert(PkgRdirectionDirectionFormulaList bean);

	/**
	 * Update.
	 * @param bean Directionformula
	 * @return Update件数
	 */
	int update(PkgRdirectionDirectionFormulaList bean);

	/**
	 * Delete.
	 * @param bean Directionformula
	 * @return Delete件数
	 */
	int delete(PkgRdirectionDirectionFormulaList bean);

	/** QUERYアノテーション findByDirectionNoStepNo(). */
	String findByDirectionNoStepNo_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_NO < 10001";

	/**
	 * 指図番号、STEP_NOに紐づくデータをすべて取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<RecipeInspection> データリスト
	 */
	List<PkgRdirectionDirectionFormulaList> findByDirectionNoStepNo(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getFinishList(). */
	String getSearchFinishList_ARGS = "directionDiv,directionNo";

	/**
	 * 包装実績－仕上げタブ一覧取得メソッド． 指図区分、指図番号、タイプ-1以外を条件に検索
	 * @param directionDiv 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionDirectionFormulaList> 一覧データ
	 */
	List<PkgRdirectionDirectionFormulaList> getSearchFinishList(
			BigDecimal directionDiv, String directionNo);

	/** QUERYアノテーション deleteFinishListByDirectionNo(). */
	String deleteFinishListByDirectionNo_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND LINE_TYPE != -1 AND LINE_NO < 10001";

	/**
	 * 包装実績－仕上げタブ フォーミュラの削除用SQL． 指図区分、指図番号、LINE_TYPE(-1以外)を条件に削除
	 * @param directionDiv 指図区分
	 * @param directionNo 指図番号
	 * @return 削除件数
	 */
	int deleteFinishListByDirectionNo(BigDecimal directionDiv,
			String directionNo);

	/** QUERYアノテーション getFinishResultQty(). */
	String getFinishResultQty_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO != -1 AND LINE_NO = 10001";

	/**
	 * 仕上数量を取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return PkgRdirectionDirectionFormulaList 検索結果
	 */
	PkgRdirectionDirectionFormulaList getFinishResultQty(
			BigDecimal directionDivision, String directionNo);

	/** QUERYアノテーション getFinishQty(). */
	String getFinishQty_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO != -1 AND LINE_NO = 1001";

	/**
	 * 仕上数量を取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return PkgRdirectionDirectionFormulaList 検索結果
	 */
	PkgRdirectionDirectionFormulaList getFinishQty(
			BigDecimal directionDivision, String directionNo);

	/** QUERYアノテーション getDivideOriginList(). */
	String getDivideOriginList_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND LINE_NO < 10001 ORDER BY LINE_NO";

	/**
	 * 分納元データを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionDirectionFormulaList> 検索結果
	 */
	List<PkgRdirectionDirectionFormulaList> getDivideOriginList(
			BigDecimal directionDivision, String directionNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo,stepNo";

	/**
	 * 配合一覧画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<PkgDirectionDirectionFormulaList> 検索結果リスト
	 */
	List<PkgRdirectionDirectionFormulaList> getList(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionDivision,directionNo,stepNo";

	/**
	 * 最終LINE_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return PkgRdirectionDirectionFormulaList 最終LINE_NO
	 */
	PkgRdirectionDirectionFormulaList getLastLineNo(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastSeq(). */
	String getLastSeq_ARGS = "directionDivision,directionNo,stepNo";

	/**
	 * 最終SEQ_NO 取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return PkgRdirectionDirectionFormulaList 最終SEQ_NO
	 */
	PkgRdirectionDirectionFormulaList getLastSeq(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo);

	/** QUERYアノテーション getFinishList(). */
	String getFinishList_QUERY = "DIRECTION_DIVISION = ? AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_NO > 1001 AND LINE_NO < 10001 AND LINE_TYPE != -1";

	/**
	 * 指図区分、指図番号、STEP_NO、LINE_NO(1001より大)、LINE_TYPE(-1以外)のデータを取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<PkgRdirectionDirectionFormulaList> 検索結果
	 */
	List<PkgRdirectionDirectionFormulaList> getFinishList(
			BigDecimal directionDivision, String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionDivision,directionNo,stepNo,lineNo";

	/**
	 * 配合詳細画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNo
	 * @param lineNo 行No
	 * @return PkgRdirectionDirectionProcedureList 検索結果
	 */
	PkgRdirectionDirectionFormulaList getEntity(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo, BigDecimal lineNo);
}
