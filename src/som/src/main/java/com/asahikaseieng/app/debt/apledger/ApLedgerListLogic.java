/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerList;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * ApLedgerListLogic.買掛元帳
 * @author tosco
 */
public interface ApLedgerListLogic {

	/**
	 * 検索処理を行う.買掛元帳
	 * @param condition condition
	 * @return List<ApLedgerList> 一覧データ
	 * @throws NoDataException NoDataException
	 */
	List<ApLedgerList> getSearchList(final ApLedgerPagerCondition condition) throws NoDataException;

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の年月を取得
	 * 
	 * @param organizationCd   部署コード
	 * @return ApRollback 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ApRollback getSearch(final String organizationCd)	throws NoDataException;

}
