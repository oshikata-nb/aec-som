/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetail;

/**
 * BalanceDetailDaoクラス
 * @author t0011036
 */
public interface BalanceDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "balanceCd";

	/**
	 * BalanceDetailメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @return BalanceDetail
	 */
	BalanceDetail getEntity(final String balanceCd);

	/** ARGSアノテーション getCountFrstOrderHead */
	String getCountFrstOrderHead_ARGS = "balanceCd";

	/**
	 * 先付受注ヘッダ存在チェック
	 * @param balanceCd 帳合コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	int getCountFrstOrderHead(final String balanceCd);

}

