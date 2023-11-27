/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.reason.Reason;
import com.asahikaseieng.dao.entity.master.reason.ReasonDao;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetailDao;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 理由詳細ロジック 実装クラス.
 * @author t0011036
 */
public class ReasonDetailLogicImpl implements ReasonDetailLogic {

	private ReasonDao reasonDao;

	private ReasonDetailDao reasonDetailDao;

	private ReasonDefaultDetailDao reasonDefaultDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ReasonDetailLogicImpl() {
	}

	/**
	 * 理由検索（登録用）
	 * @param ryCd 理由コード
	 * @return Reason
	 * @throws NoDataException NoDataException
	 */
	public Reason getEntity(final String ryCd) throws NoDataException {
		if (StringUtils.isEmpty(ryCd)) {
			throw new IllegalArgumentException("ryCd is empty");
		}

		Reason bean = reasonDao.getEntity(ryCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 理由検索（詳細用）
	 * @param ryCd 理由コード
	 * @return ReasonDetail
	 */
	public ReasonDetail getDetailEntity(final String ryCd) {
		ReasonDetail bean = reasonDetailDao.getEntity(ryCd);
		return bean;
	}

	/**
	 * デフォルト理由チェック
	 * @return ReasonDefaultDetail
	 */
	public ReasonDefaultDetail chkDefault() {
		ReasonDefaultDetail bean = reasonDefaultDetailDao.getEntity();
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Reason bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			reasonDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Reason bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			reasonDao.insert(bean);
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
	public void delete(final Reason bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			reasonDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * reasonDaoを設定します。
	 * @param reasonDao reasonDao
	 */
	public void setReasonDao(final ReasonDao reasonDao) {
		this.reasonDao = reasonDao;
	}

	/**
	 * reasonDetailDaoを設定します。
	 * @param reasonDetailDao reasonDetailDao
	 */
	public void setReasonDetailDao(final ReasonDetailDao reasonDetailDao) {
		this.reasonDetailDao = reasonDetailDao;
	}

	/**
	 * reasonDefaultDetailDaoを設定します。
	 * @param reasonDefaultDetailDao reasonDefaultDetailDao
	 */
	public void setReasonDefaultDetailDao(
			final ReasonDefaultDetailDao reasonDefaultDetailDao) {
		this.reasonDefaultDetailDao = reasonDefaultDetailDao;
	}
}
