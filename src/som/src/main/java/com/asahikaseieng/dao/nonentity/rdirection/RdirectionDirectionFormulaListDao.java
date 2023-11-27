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
 * 製造指図フォーミュラDaoインターフェース.
 *
 * @author tosco
 */
public interface RdirectionDirectionFormulaListDao {

	/** BEANアノテーション */
	Class<RdirectionDirectionFormulaList> BEAN = RdirectionDirectionFormulaList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionformula
	 * @return Insert件数
	 */
	int insert(RdirectionDirectionFormulaList bean);

	/**
	 * Update.
	 * @param bean Directionformula
	 * @return Update件数
	 */
	int update(RdirectionDirectionFormulaList bean);

	/**
	 * Delete.
	 * @param bean Directionformula
	 * @return Delete件数
	 */
	int delete(RdirectionDirectionFormulaList bean);

	/**
	 * nullではないフィールドのデータのみを更新する
	 * @param bean RdirectionDirectionFormulaList
	 * @return 更新件数
	 */
	int updateUnlessNull(RdirectionDirectionFormulaList bean);

	/** ARGSアノテーション getFinishList(). */
	String getSearchFinishList_ARGS = "directionNo";
	/**
	 * 製造実績－仕上げタブ一覧取得メソッド．
	 * 	（指図区分(1)、指図番号、タイプ-1以外で検索）
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionFormulaList> 一覧データ
	 */
	List<RdirectionDirectionFormulaList> getSearchFinishList(String directionNo);

	/** QUERYアノテーション deleteFinishListByDirectionNo(). */
	String deleteFinishListByDirectionNo_QUERY = "DIRECTION_DIVISION = 1 AND DIRECTION_NO = ? AND LINE_TYPE != -1";
	/**
	 * 製造実績フォーミュラ-仕上げタブの削除用SQL．
	 *   指図区分(1)、指図番号、LINE_TYPE(-1以外)で削除
	 * @param directionNo 指図番号
	 * @return 削除件数
	 */
	int deleteFinishListByDirectionNo(String directionNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionNo,stepNo";
	/**
	 * 指図番号、STEP_NOからフォーミュラデータを取得する
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return List<RdirectionDirectionFormulaList>
	 */
	List<RdirectionDirectionFormulaList> getList(String directionNo, BigDecimal stepNo);

	/** QUERYアノテーション getFinishList(). */
	String getFinishList_QUERY =
		"DIRECTION_DIVISION = 1 AND DIRECTION_NO = ? AND STEP_NO = ? AND LINE_NO > 1001 AND LINE_TYPE != -1";
	/**
	 * 指図区分(1)、指図番号、STEP_NO、LINE_NO(1001より大)、LINE_TYPE(-1以外)のデータを取得
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return List<RdirectionDirectionFormulaList> 検索結果
	 */
	List<RdirectionDirectionFormulaList> getFinishList(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo,procStepNo";
	/**
	 * 指図番号、STEP_NOからフォーミュラデータを取得する
	 * @param directionNo 指図番号
	 * @param procStepNo STEP_NO
	 * @return List<RdirectionDirectionFormulaList> 検索結果
	 */
	List<RdirectionDirectionFormulaList> getSearchList(String directionNo, BigDecimal procStepNo);

	/** ARGSアノテーション getLastLineNo(). */
	String getLastLineNo_ARGS = "directionNo,stepNo";
	/**
	 * 最終LINE_NO[指図区分(1)] 取得.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return RdirectionDirectionFormulaList 最終LINE_NO
	 */
	RdirectionDirectionFormulaList getLastLineNo(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getLastSeq(). */
	String getLastSeq_ARGS = "directionNo,stepNo";
	/**
	 * 最終SEQ[指図区分(1)] 取得.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return RdirectionDirectionFormulaList 最終SEQ
	 */
	RdirectionDirectionFormulaList getLastSeq(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getByPrimaryKey(). */
	String getByPrimaryKey_ARGS = "directionNo,stepNo,lineNo";
	/**
	 * 指図区分(1)、指図番号、STEP_NO、LINE_NOで検索
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @param lineNo   LINE_NO
	 * @return RdirectionDirectionFormulaList 検索結果
	 */
	RdirectionDirectionFormulaList getByPrimaryKey(String directionNo, BigDecimal stepNo, BigDecimal lineNo);

}
