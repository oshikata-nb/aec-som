/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import com.asahikaseieng.dao.entity.master.accounts.Accounts;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 勘定科目詳細ロジック interface.
 * @author t0011036
 */
public interface AccountsDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param accountsCd 勘定科目コード
	 * @throws NoDataException NoDataException
	 * @return Accounts
	 */
	Accounts getEntity(final String accountsCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	AccountsDetail getDetailEntity(final String accountsCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Accounts bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Accounts bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Accounts bean) throws NoDataException;
}
