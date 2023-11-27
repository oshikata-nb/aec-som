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
 * 製造指図プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface RdirectionDirectionProcedureListDao {

	/** BEANアノテーション */
	Class<RdirectionDirectionProcedureList> BEAN = RdirectionDirectionProcedureList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionprocedure
	 * @return Insert件数
	 */
	int insert(RdirectionDirectionProcedureList bean);

	/**
	 * Update.
	 * @param bean Directionprocedure
	 * @return Update件数
	 */
	int update(RdirectionDirectionProcedureList bean);

	/**
	 * Delete.
	 * @param bean Directionprocedure
	 * @return Delete件数
	 */
	int delete(RdirectionDirectionProcedureList bean);

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "directionNo";
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionProcedureList> 検索結果リスト
	 */
	List<RdirectionDirectionProcedureList> getSearchList(String directionNo);

	/** ARGSアノテーション getLastStepNo(). */
	String getLastStepNo_ARGS = "directionNo";
	/**
	 * LAST_STEP_NO取得
	 * @param directionNo 指図番号
	 * @return RdirectionDirectionProcedureList LAST_STEP_NO
	 */
	RdirectionDirectionProcedureList getLastStepNo(String directionNo);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionNo";
	/**
	 * 指図区分、指図番号で削除
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(String directionNo);

	/** ARGSアノテーション getOperationName(). */
	String getOperationName_ARGS = "directionNo,stepNo";
	/**
	 * 工程コード、工程名称 取得.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return RdirectionDirectionProcedureList 工程コード、工程名称
	 */
	RdirectionDirectionProcedureList getOperationName(String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getByPrimaryKey(). */
	String getByPrimaryKey_ARGS = "directionNo,stepNo";
	/**
	 * 工程タブ詳細取得
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return RdirectionDirectionProcedureList
	 */
	RdirectionDirectionProcedureList getByPrimaryKey(String directionNo, BigDecimal stepNo);

}
