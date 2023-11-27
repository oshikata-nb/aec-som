/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.bank.Bank;
import com.asahikaseieng.dao.entity.master.bank.BankDao;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 銀行詳細ロジック 実装クラス.
 * @author t0011036
 */
public class BankDetailLogicImpl implements BankDetailLogic {

	private BankDao bankDao;

	private BankDetailDao bankDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BankDetailLogicImpl() {
	}

	/**
	 * 銀行検索（登録用）
	 * @param bankMasterCd 銀行マスタコード
	 * @return Bank
	 * @throws NoDataException NoDataException
	 */
	public Bank getEntity(final String bankMasterCd) throws NoDataException {
		if (StringUtils.isEmpty(bankMasterCd)) {
			throw new IllegalArgumentException("bankMasterCd is empty");
		}

		Bank bean = bankDao.getEntity(bankMasterCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 銀行検索（詳細用）
	 * @param bankMasterCd 銀行マスタコード
	 * @return BankDetail
	 */
	public BankDetail getDetailEntity(final String bankMasterCd) {
		BankDetail bean = bankDetailDao.getEntity(bankMasterCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Bank bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			bankDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Bank bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			bankDao.insert(bean);
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
	public void delete(final Bank bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			bankDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * bankDaoを設定します。
	 * @param bankDao bankDao
	 */
	public void setBankDao(final BankDao bankDao) {
		this.bankDao = bankDao;
	}

	/**
	 * bankDetailDaoを設定します。
	 * @param bankDetailDao bankDetailDao
	 */
	public void setBankDetailDao(final BankDetailDao bankDetailDao) {
		this.bankDetailDao = bankDetailDao;
	}
}
