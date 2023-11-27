/*
 * Created on 2007/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.operation;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchList;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchListDao;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程マスタ検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class OperationSearchLogicImpl implements OperationSearchLogic {

	/** 工程マスタ検索(ポップアップ)Dao */
	private OperationSearchListDao operationSearchListDao;

	/**
	 * コンストラクタ.
	 */
	public OperationSearchLogicImpl() {
	}

	/**
	 * 工程マスタ検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OperationSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<OperationSearchList> getList(final OperationSearchListPagerCondition condition)
		throws NoDataException {

		checkParams(condition);

		List<OperationSearchList> list = operationSearchListDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OperationSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 工程マスタ検索(ポップアップ)Daoを設定します。
	 * @param operationSearchListDao 工程マスタ検索(ポップアップ)Dao
	 */
	public void setOperationSearchDao(final OperationSearchListDao operationSearchListDao) {
		this.operationSearchListDao = operationSearchListDao;
	}
}
