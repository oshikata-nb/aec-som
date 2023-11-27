/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.operationdetail.OperationDetail;
import com.asahikaseieng.dao.nonentity.master.operationdetail.OperationDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 工程詳細ロジック 実装クラス.
 * @author t0011036
 */
public class OperationDetailLogicImpl implements OperationDetailLogic {

	private OperationDao operationDao;

	private OperationDetailDao operationDetailDao;

	private OperationGroupListForComboboxesDao operationGroupListForComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public OperationDetailLogicImpl() {
	}

	/**
	 * 工程検索（登録用）
	 * @param operationCd 工程コード
	 * @return Operation
	 * @throws NoDataException NoDataException
	 */
	public Operation getEntity(final String operationCd) throws NoDataException {
		if (StringUtils.isEmpty(operationCd)) {
			throw new IllegalArgumentException("operationCd is empty");
		}

		Operation bean = operationDao.getEntity(operationCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 工程検索（詳細用）
	 * @param operationCd 工程コード
	 * @return OperationDetail
	 */
	public OperationDetail getDetailEntity(final String operationCd) {
		OperationDetail bean = operationDetailDao.getEntity(operationCd);
		return bean;
	}

	/**
	 * 工程グループリスト取得
	 * @return List<OperationGroupListForComboboxes>
	 */
	public List<OperationGroupListForComboboxes> getOperationGroupList() {
		List<OperationGroupListForComboboxes> list = operationGroupListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Operation bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			operationDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Operation bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			operationDao.insert(bean);
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
	public void delete(final Operation bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			operationDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * operationDaoを設定します。
	 * @param operationDao operationDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

	/**
	 * operationDetailDaoを設定します。
	 * @param operationDetailDao operationDetailDao
	 */
	public void setOperationDetailDao(
			final OperationDetailDao operationDetailDao) {
		this.operationDetailDao = operationDetailDao;
	}

	/**
	 * operationGroupListForComboboxesDaoを設定します。
	 * @param operationGroupListForComboboxesDao
	 *            operationGroupListForComboboxesDao
	 */
	public void setOperationGroupListForComboboxesDao(
			final OperationGroupListForComboboxesDao operationGroupListForComboboxesDao) {
		this.operationGroupListForComboboxesDao = operationGroupListForComboboxesDao;
	}
}
