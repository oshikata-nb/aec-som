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
 * 包装指図－製造指図プロシージャDaoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionDirectionProcedureListDao {

	/** BEANアノテーション */
	Class<PkgDirectionDirectionProcedureList> BEAN = PkgDirectionDirectionProcedureList.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Directionprocedure
	 * @return Insert件数
	 */
	int insert(PkgDirectionDirectionProcedureList bean);

	/**
	 * Update.
	 * @param bean Directionprocedure
	 * @return Update件数
	 */
	int update(PkgDirectionDirectionProcedureList bean);

	/**
	 * Delete.
	 * @param bean Directionprocedure
	 * @return Delete件数
	 */
	int delete(PkgDirectionDirectionProcedureList bean);

	/** ARGSアノテーション deleteByDirectionNo(). */
	String deleteByDirectionNo_ARGS = "directionDivision,directionNo";
	/**
	 * 包装指図番号に紐づくデータをすべて削除
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return Delete件数
	 */
	int deleteByDirectionNo(BigDecimal directionDivision, String directionNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "directionDivision,directionNo";
	/**
	 * 工程一覧画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgDirectionDirectionProcedureList> 検索結果リスト
	 */
	List<PkgDirectionDirectionProcedureList> getList(BigDecimal directionDivision, String directionNo);

	/** ARGSアノテーション getLastStepNo(). */
	String getLastStepNo_ARGS = "directionDivision,directionNo";
	/**
	 * 指図区分、指図番号内容での最終ステップNOを取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return PkgDirectionDirectionProcedureList 検索結果
	 */
	PkgDirectionDirectionProcedureList getLastStepNo(BigDecimal directionDivision, String directionNo);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "directionDivision,directionNo,stepNo";
	/**
	 * 工程詳細画面表示内容を取得
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return PkgDirectionDirectionProcedureList 検索結果
	 */
	PkgDirectionDirectionProcedureList getEntity(BigDecimal directionDivision,
			String directionNo, BigDecimal stepNo);

	/** ARGSアノテーション getOperationName(). */
	String getOperationName_ARGS = "directionNo,stepNo";
	/**
	 * 工程コード、工程名称 取得.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return DirectionDirectionProcedureList 工程コード、工程名称
	 */
	PkgDirectionDirectionProcedureList getOperationName(String directionNo, BigDecimal stepNo);

}
