/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.financialclass.FinancialClass;
import com.asahikaseieng.dao.entity.master.financialclass.FinancialClassDao;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 財務分類詳細ロジック 実装クラス.
 * @author t0011036
 */
public class FinancialClassDetailLogicImpl implements FinancialClassDetailLogic {

	private FinancialClassDao financialClassDao;

	private FinancialClassDetailDao financialClassDetailDao;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassDetailLogicImpl() {
	}

	/**
	 * 財務分類検索（登録用）
	 * @param financialClassCd 財務分類コード
	 * @return FinancialClass
	 * @throws NoDataException NoDataException
	 */
	public FinancialClass getEntity(final String financialClassCd)
			throws NoDataException {
		if (StringUtils.isEmpty(financialClassCd)) {
			throw new IllegalArgumentException("financialClassCd is empty");
		}

		FinancialClass bean = financialClassDao.getEntity(financialClassCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 財務分類検索（詳細用）
	 * @param financialClassCd 財務分類コード
	 * @return FinancialClassDetail
	 */
	public FinancialClassDetail getDetailEntity(final String financialClassCd) {
		FinancialClassDetail bean = financialClassDetailDao
				.getEntity(financialClassCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final FinancialClass bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			financialClassDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final FinancialClass bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			financialClassDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final FinancialClass bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			financialClassDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * financialClassDaoを設定します。
	 * @param financialClassDao financialClassDao
	 */
	public void setFinancialClassDao(final FinancialClassDao financialClassDao) {
		this.financialClassDao = financialClassDao;
	}

	/**
	 * financialClassDetailDaoを設定します。
	 * @param financialClassDetailDao financialClassDetailDao
	 */
	public void setFinancialClassDetailDao(
			final FinancialClassDetailDao financialClassDetailDao) {
		this.financialClassDetailDao = financialClassDetailDao;
	}
}
