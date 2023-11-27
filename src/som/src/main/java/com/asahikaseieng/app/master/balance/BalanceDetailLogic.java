/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.util.List;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.balancedetaillist.BalanceDetailList;
import com.asahikaseieng.dao.nonentity.master.balancelowerdetail.BalanceLowerDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 帳合詳細ロジック interface.
 * @author t0011036
 */
public interface BalanceDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @throws NoDataException NoDataException
	 * @return Balance
	 */
	Balance getEntity(final String balanceCd) throws NoDataException;

	/**
	 * 下位帳合検索処理を行う.
	 * @param upperBalanceCd 上位帳合コード
	 * @throws NoDataException NoDataException
	 * @return Balance
	 */
	BalanceLowerDetail getLowerEntity(final String upperBalanceCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	BalanceDetail getDetailEntity(final String balanceCd);

	/**
	 * 検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @return List<BalanceDetailList>
	 */
	List<BalanceDetailList> getDetailList(final String balanceCd);

	/**
	 * 得意先検索処理を行う.
	 * @param venderDivision 取引先区分
	 * @param venderCd 得意先コード
	 * @return VenderDetail
	 */
	VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 帳合レベル計算
	 * @param balanceCd 帳合コード
	 * @param upperBalanceCd 上位帳合コード
	 * @throws NoDataException NoDataException
	 * @return 帳合レベル
	 */
	int calcBalanceLevel(final String balanceCd, final String upperBalanceCd)
			throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Balance bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Balance bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Balance bean) throws NoDataException;

	/**
	 * 先付受注ヘッダ存在チェック用
	 * @param balanceCd 帳合コード
	 * @return ある・無し値
	 */
	int getCountFrstOrderHead(final String balanceCd);

}
