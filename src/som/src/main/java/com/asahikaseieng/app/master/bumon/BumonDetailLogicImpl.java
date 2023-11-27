/*
 * Created on 2007/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.bumon.Bumon;
import com.asahikaseieng.dao.entity.master.bumon.BumonDao;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 会計部門マスタ詳細 ロジック実装クラス
 * @author TanakaSatoru
 */
public class BumonDetailLogicImpl implements BumonDetailLogic {

	private BumonDetailDao bumonDetailDao;

	private BumonDao bumonDao;

	/**
	 * コンストラクタ.会計部門マスタ詳細ロジック
	 */
	public BumonDetailLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Bumon getEntity(final String sectionCd) throws NoDataException {
		if (StringUtils.isEmpty(sectionCd)) {
			throw new IllegalArgumentException("sectionCd is empty");
		}

		Bumon bean = bumonDao.getEntity(sectionCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public BumonDetail getDetailEntity(final String sectionCd)
			throws NoDataException {
		BumonDetail bean = bumonDetailDao.getEntity(sectionCd);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(final Bumon bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			bumonDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(final Bumon bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			bumonDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Bumon bean) throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			bumonDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * bumonDetailDaoを設定します。
	 * @param bumonDetailDao BumonDetailDao
	 */
	public void setBumonDetailDao(final BumonDetailDao bumonDetailDao) {
		this.bumonDetailDao = bumonDetailDao;
	}

	/**
	 * bumonDaoを設定します。
	 * @param bumonDao bumonDao
	 */
	public void setBumonDao(final BumonDao bumonDao) {
		this.bumonDao = bumonDao;
	}
}
