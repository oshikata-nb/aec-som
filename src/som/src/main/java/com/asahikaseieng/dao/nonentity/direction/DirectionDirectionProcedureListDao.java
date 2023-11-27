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
 * 製造指図プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface DirectionDirectionProcedureListDao {

	/** BEANアノテーション */
	Class<DirectionDirectionProcedureList> BEAN = DirectionDirectionProcedureList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionprocedure
	 * @return Insert件数
	 */
	int insert(DirectionDirectionProcedureList bean);

	/**
	 * Update.
	 * @param bean Directionprocedure
	 * @return Update件数
	 */
	int update(DirectionDirectionProcedureList bean);

	/**
	 * Delete.
	 * @param bean Directionprocedure
	 * @return Delete件数
	 */
	int delete(DirectionDirectionProcedureList bean);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo";
	/**
	 * 指図区分、指図番号で検索
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionProcedureList> 検索結果リスト
	 */
	List<DirectionDirectionProcedureList> getSearchList(String directionNo);

	/** ARGSアノテーション getLastStepNo(). */
	String getLastStepNo_ARGS = "directionNo";
	/**
	 * LAST_STEP_NO取得
	 * @param directionNo 指図番号
	 * @return DirectionDirectionProcedureList LAST_STEP_NO
	 */
	DirectionDirectionProcedureList getLastStepNo(String directionNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図区分、指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);

	/** ARGSアノテーション getByPrimaryKey(). */
	String getByPrimaryKey_ARGS = "directionNo,stepNo";
	/**
	 * 工程タブ詳細取得
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return DirectionDirectionProcedureList LAST_STEP_NO
	 */
	DirectionDirectionProcedureList getByPrimaryKey(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getOperationName(). */
	String getOperationName_ARGS = "directionNo,stepNo";
	/**
	 * 工程コード、工程名称 取得.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return DirectionDirectionProcedureList 工程コード、工程名称
	 */
	DirectionDirectionProcedureList getOperationName(String directionNo, BigDecimal stepNo);
}
