/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.operation;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchList;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程マスタ検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface OperationSearchLogic {

	/**
	 * 工程マスタ検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OperationSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<OperationSearchList> getList(final OperationSearchListPagerCondition condition) throws NoDataException;
}
