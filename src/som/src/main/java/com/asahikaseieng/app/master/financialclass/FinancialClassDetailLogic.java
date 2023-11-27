/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import com.asahikaseieng.dao.entity.master.financialclass.FinancialClass;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 財務分類詳細ロジック interface.
 * @author t0011036
 */
public interface FinancialClassDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param financialClassCd 財務分類コード
	 * @throws NoDataException NoDataException
	 * @return FinancialClass
	 */
	FinancialClass getEntity(final String financialClassCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param financialClassCd 財務分類コード
	 * @return FinancialClassDetail
	 */
	FinancialClassDetail getDetailEntity(final String financialClassCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final FinancialClass bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final FinancialClass bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final FinancialClass bean) throws NoDataException;
}
