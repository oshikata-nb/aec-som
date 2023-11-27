/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績 工程詳細 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionProcedureDetailLogic {

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return RdirectionDirectionProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	RdirectionDirectionProcedureList getByPrimaryKey(
			final String directionNo, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param bean 製造指図プロシージャBean
	 * @throws Exception 例外
	 */
	void update(final RdirectionDirectionProcedureList bean) throws Exception;

}
