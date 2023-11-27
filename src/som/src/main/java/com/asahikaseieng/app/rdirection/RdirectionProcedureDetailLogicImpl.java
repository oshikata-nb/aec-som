/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造実績 工程詳細 ロジック実装クラス
 * @author tosco
 */
public class RdirectionProcedureDetailLogicImpl implements
		RdirectionProcedureDetailLogic {

	/** 製造指図プロシージャDao */
	private RdirectionDirectionProcedureListDao rdirectionProcedureDao;

	/**
	 * コンストラクタ
	 */
	public RdirectionProcedureDetailLogicImpl() {
	}

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return RdirectionDirectionProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RdirectionDirectionProcedureList getByPrimaryKey(
			final String directionNo, final BigDecimal stepNo)
			throws NoDataException {

		RdirectionDirectionProcedureList bean = rdirectionProcedureDao
				.getByPrimaryKey(directionNo, stepNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param bean 製造指図プロシージャBean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RdirectionDirectionProcedureList bean)
			throws Exception {
		try {
			// 更新処理
			rdirectionProcedureDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図プロシージャDao を設定します。
	 * @param rdirectionProcedureDao 製造指図プロシージャDao
	 */
	public void setRdirectionProcedureDao(
			final RdirectionDirectionProcedureListDao rdirectionProcedureDao) {
		this.rdirectionProcedureDao = rdirectionProcedureDao;
	}

}
