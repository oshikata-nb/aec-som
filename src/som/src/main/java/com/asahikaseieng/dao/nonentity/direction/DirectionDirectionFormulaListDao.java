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
 * 製造指図フォーミュラDaoインターフェース.
 * 
 * @author tosco
 */
public interface DirectionDirectionFormulaListDao {

	/** BEANアノテーション */
	Class<DirectionDirectionFormulaList> BEAN = DirectionDirectionFormulaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionformula
	 * @return Insert件数
	 */
	int insert(DirectionDirectionFormulaList bean);

	/**
	 * Update.
	 * @param bean Directionformula
	 * @return Update件数
	 */
	int update(DirectionDirectionFormulaList bean);

	/**
	 * Delete.
	 * @param bean Directionformula
	 * @return Delete件数
	 */
	int delete(DirectionDirectionFormulaList bean);

	/** ARGSアノテーション getTankList(). */
	String getTankList_ARGS = "directionNo";

	/**
	 * 指図番号からフォームラデータに対応するタンク一覧を取得する
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionFormulaList>
	 */
	List<DirectionDirectionFormulaList> getTankList(String directionNo);

	/**
	 * nullではないフィールドのデータのみを更新する
	 * @param bean Directionformula
	 * @return 更新件数
	 */
	int updateUnlessNull(DirectionDirectionFormulaList bean);

	/** ARGSアノテーション updateTanks(). */
	String updateTanks_ARGS = "condition";

	/**
	 * タンク情報のみを更新する
	 * @param bean Directionformula
	 * @return 更新件数
	 */
	int updateTanks(DirectionDirectionFormulaList bean);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionNo,stepNo";

	/**
	 * 指図番号、STEP_NOからフォームラデータを取得する
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<DirectionDirectionFormulaList>
	 */
	List<DirectionDirectionFormulaList> getList(String directionNo,
			BigDecimal stepNo);

	/** ARGSアノテーション getSumQty(). */
	String getSumQty_ARGS = "directionNo,procStepNo";

	/**
	 * 指図番号、STEP_NOでごとの合計を取得する
	 * @param directionNo 指図番号
	 * @param procStepNo STEP_NO
	 * @return DirectionDirectionFormulaList
	 */
	DirectionDirectionFormulaList getSumQty(String directionNo,
			BigDecimal procStepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo,procStepNo";

	/**
	 * 配合タグ一覧の検索取得[指図区分(1)]
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return List<DirectionDirectionFormulaList> データリスト
	 */
	List<DirectionDirectionFormulaList> getSearchList(String directionNo,
			BigDecimal procStepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchAllList_ARGS = "directionNo,directionDivision";

	/**
	 * 配合タグ一覧の検索取得[指図区分(1)]
	 * @param directionNo 指図番号
	 * @param directionDivision 工程順序
	 * @return List<DirectionDirectionFormulaList> データリスト
	 */
	List<DirectionDirectionFormulaList> getSearchAllList(String directionNo,
			BigDecimal directionDivision);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionNo,stepNo";

	/**
	 * 最終LINE_NO[指図区分(1)] 取得.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return DirectionDirectionFormulaList 最終LINE_NO
	 */
	DirectionDirectionFormulaList getLastLineNo(String directionNo,
			BigDecimal stepNo);

	/** ARGSアノテーション getLastSeq(). */
	String getLastSeq_ARGS = "directionNo,stepNo";

	/**
	 * 最終SEQ[指図区分(1)] 取得.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return DirectionDirectionFormulaList 最終SEQ
	 */
	DirectionDirectionFormulaList getLastSeq(String directionNo,
			BigDecimal stepNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";

	/**
	 * 指図区分(1)、指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);

	/** ARGSアノテーション getByPrimaryKey(). */
	String getByPrimaryKey_ARGS = "directionNo,stepNo,lineNo";

	/**
	 * 指図区分(1)、指図番号、STEP_NO、LINE_NOで検索
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return DirectionDirectionFormulaList 検索結果
	 */
	DirectionDirectionFormulaList getByPrimaryKey(String directionNo,
			BigDecimal stepNo, BigDecimal lineNo);

	/** ARGSアノテーション getSearchFinishList(). */
	String getSearchFinishList_ARGS = "directionNo";

	/**
	 * 指図区分(1)、指図番号、タイプ-1以外で検索
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionFormulaList> 検索結果
	 */
	List<DirectionDirectionFormulaList> getSearchFinishList(String directionNo);

	/** QUERYアノテーション deleteFinishListByDirectionNo(). */
	String deleteFinishListByDirectionNo_QUERY = "DIRECTION_DIVISION = 1 AND DIRECTION_NO = ? AND LINE_TYPE != -1";

	/**
	 * 指図区分(1)、指図番号、LINE_TYPE(-1以外)に紐づくデータをすべて削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteFinishListByDirectionNo(String directionNo);

	/** QUERYアノテーション getFinishList(). */
	String getFinishList_QUERY = "DIRECTION_DIVISION = 1 AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_NO > 1001 AND LINE_TYPE != -1";

	/**
	 * 指図区分(1)、指図番号、STEP_NO、LINE_NO(1001より大)、LINE_TYPE(-1以外)のデータを取得
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<DirectionDirectionFormulaList> 検索結果
	 */
	List<DirectionDirectionFormulaList> getFinishList(String directionNo,
			BigDecimal stepNo);

}
