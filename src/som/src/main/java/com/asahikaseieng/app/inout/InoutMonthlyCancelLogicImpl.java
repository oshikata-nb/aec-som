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
 * 月次受払ロールバック処理ロジック 実装クラス.
 * @author t0011036
 */
public class InoutMonthlyCancelLogicImpl implements InoutMonthlyCancelLogic {

	private InoutMonthlyUpdateDao inoutMonthlyUpdateDao;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyCancelLogicImpl() {
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
	 * 月次更新削除処理
	 * @param inputDate 処理年月
	 * @return boolean
	 * @throws Exception Exception
	 */
	public boolean deleteMonthly(final String inputDate) throws Exception {

		try {
			/* 作成処理 */
			inoutMonthlyUpdateDao.deleteMonthly(inputDate);
			return true;
		} catch (SQLRuntimeException runtimeExpt) {

			throw new OptimisticLockRuntimeException(0);
		}
	}

	/**
	 * 受払更新取消処理
	 * @param inputDateFrom 処理開始日
	 * @param inputDateTo 処理終了日
	 * @param tantoCd 担当者コード
	 * @return boolean
	 * @throws Exception Exception
	 */
	public boolean updateInoutCancel(final String inputDateFrom,
			final String inputDateTo, final String tantoCd) throws Exception {

		try {
			/* 作成処理 */
			inoutMonthlyUpdateDao.updateInoutCancel(inputDateFrom, inputDateTo,
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
