/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import com.asahikaseieng.dao.entity.master.bank.Bank;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 銀行詳細ロジック interface.
 * @author t0011036
 */
public interface BankDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param bankMasterCd 銀行マスタコード
	 * @throws NoDataException NoDataException
	 * @return Bank
	 */
	Bank getEntity(final String bankMasterCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param bankMasterCd 銀行マスタコード
	 * @return BankDetail
	 */
	BankDetail getDetailEntity(final String bankMasterCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Bank bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Bank bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Bank bean) throws NoDataException;
}
