/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan;

import java.util.List;


/**
 * 購買計画一覧要DAO
 * @author tosco
 */
public interface PurchasePlanListDao {
	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan.PurchasePlanList.class;

	/** ARGSアノテーション getExcelList */
	String getExcelList_ARGS = "condition";

	/**
	 * 帳票出力用検索メソッド
	 * @param condition condition
	 * @return List<PurchasePlanList>
	 */
	List<PurchasePlanList> getExcelList(final PurchasePlanPagerCondition condition);

	/** ARGSアノテーション getExcelList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧画面表示用検索メソッド
	 * @param condition condition
	 * @return List<PurchasePlanList>
	 */
	List<PurchasePlanList> getSearchList(final PurchasePlanPagerCondition condition);

}
