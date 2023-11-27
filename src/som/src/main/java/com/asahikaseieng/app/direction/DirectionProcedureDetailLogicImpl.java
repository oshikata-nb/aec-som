/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class DirectionProcedureDetailLogicImpl implements DirectionProcedureDetailLogic {

	/** 製造指図プロシージャDao */
	private DirectionDirectionProcedureListDao directionProcedureDao;

	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public DirectionProcedureDetailLogicImpl() {
	}

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return DirectionDirectionProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public DirectionDirectionProcedureList getByPrimaryKey(
			final String directionNo, final BigDecimal stepNo) throws NoDataException {

		DirectionDirectionProcedureList bean = directionProcedureDao.getByPrimaryKey(directionNo, stepNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 処方プロシージャ更新処理を行う.
	 * @param bean 製造指図プロシージャBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception データが存在しない例外
	 */
	public void update(final DirectionDirectionProcedureList bean,
			final DirectionDirectionHeaderList header) throws Exception {
		try {
			//処方ヘッダ更新
			directionCommonsLogic.update(header);

			// 更新処理
			directionProcedureDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図プロシージャDao を設定します。
	 * @param directionProcedureDao 製造指図プロシージャDao
	 */
	public void setDirectionProcedureDao(
			final DirectionDirectionProcedureListDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}
	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
}
