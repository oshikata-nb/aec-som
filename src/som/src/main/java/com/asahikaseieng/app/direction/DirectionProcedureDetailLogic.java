/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本製造指図 製造指図プロシージャ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionProcedureDetailLogic {

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return DirectionDirectionProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	DirectionDirectionProcedureList getByPrimaryKey(
			final String directionNo, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param bean 製造指図プロシージャBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception 例外
	 */
	void update(final DirectionDirectionProcedureList bean,
			final DirectionDirectionHeaderList header) throws Exception;

}
