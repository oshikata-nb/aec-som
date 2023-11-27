/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.inoutmonthlyupdate.InoutMonthlyUpdate;
import com.asahikaseieng.dao.nonentity.inoutmonthlyupdate.InoutMonthlyUpdateDao;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 月次受払更新処理ロジック 実装クラス.
 * @author t0011036
 */
public class InoutMonthlyUpdateLogicImpl implements InoutMonthlyUpdateLogic {

	private InoutMonthlyUpdateDao inoutMonthlyUpdateDao;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyUpdateLogicImpl() {
	}

	/**
	 * 受払件数取得処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @return int
	 * @throws Exception Exception
	 */
	public int getInoutCount(final String inputDateFrom,
			final String inputDateTo) throws Exception {
		InoutMonthlyUpdate bean = inoutMonthlyUpdateDao.getInoutCount(
			inputDateFrom, inputDateTo);

		if (bean == null) {
			return 0;
		}

		return bean.getCnt().intValue();
	}

	/**
	 * 月次件数取得処理
	 * @param inputDate 処理年月
	 * @return int
	 * @throws Exception Exception
	 */
	public int getMonthlyCount(final String inputDate) throws Exception {
		InoutMonthlyUpdate bean = inoutMonthlyUpdateDao
				.getMonthlyCount(inputDate);

		if (bean == null) {
			return 0;
		}

		return bean.getCnt().intValue();
	}

	/**
	 * 月次更新作成処理
	 * @param inputDate 処理年月
	 * @param inputPrevDate 前月処理年月
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return boolean
	 * @throws Exception Exception
	 */
	public boolean insertMonthly(final String inputDate,
			final String inputPrevDate, final String inputDateFrom,
			final String inputDateTo, final String tantoCd) throws Exception {

		try {
			/* 作成処理 */
			inoutMonthlyUpdateDao.insertMonthly(inputDate, inputDateFrom,
				inputDateTo, tantoCd);

			/* 月次更新未作成分作成処理 */
			inoutMonthlyUpdateDao.insertDifferenceMonthly(inputDate,
				inputPrevDate, tantoCd);
		} catch (SQLRuntimeException runtimeExpt) {

			throw new OptimisticLockRuntimeException(0);
		}

		return true;
	}

	/**
	 * 受払更新処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return boolean
	 * @throws Exception Exception
	 */
	public boolean updateInout(final String inputDateFrom,
			final String inputDateTo, final String tantoCd) throws Exception {

		try {
			/* 作成処理 */
			inoutMonthlyUpdateDao.updateInout(inputDateFrom, inputDateTo,
				tantoCd);
			return true;
		} catch (SQLRuntimeException runtimeExpt) {
			throw new OptimisticLockRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutMonthlyUpdateDaoを設定します。
	 * @param inoutMonthlyUpdateDao inoutMonthlyUpdateDao
	 */
	public void setInoutMonthlyUpdateDao(
			final InoutMonthlyUpdateDao inoutMonthlyUpdateDao) {
		this.inoutMonthlyUpdateDao = inoutMonthlyUpdateDao;
	}
}
