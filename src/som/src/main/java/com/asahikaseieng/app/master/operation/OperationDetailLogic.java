/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.List;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.operationdetail.OperationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程詳細ロジック interface.
 * @author t0011036
 */
public interface OperationDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param operationCd 工程コード
	 * @throws NoDataException NoDataException
	 * @return Operation
	 */
	Operation getEntity(final String operationCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param operationCd 工程コード
	 * @return OperationDetail
	 */
	OperationDetail getDetailEntity(final String operationCd);

	/**
	 * 工程グループ取得
	 * @return List<OperationGroupListForComboboxes>
	 */
	List<OperationGroupListForComboboxes> getOperationGroupList();

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Operation bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Operation bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Operation bean) throws NoDataException;
}
