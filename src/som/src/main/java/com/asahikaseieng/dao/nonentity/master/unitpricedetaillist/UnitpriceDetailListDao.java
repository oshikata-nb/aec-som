/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricedetaillist;

import java.math.BigDecimal;
import java.util.List;

/**
 * UnitpriceDetailListDaoクラス
 * @author kanri-user
 */
public interface UnitpriceDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "venderDivision,venderCd,itemCd,version";

	/**
	 * Listメソッド
	 * @param venderDivision 取引先区分
	 * @param venderCd 仕入先コード
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return List<UnitpriceDetailList>
	 */
	List<UnitpriceDetailList> getList(final String venderDivision,
			final String venderCd, final String itemCd, final BigDecimal version);
}
