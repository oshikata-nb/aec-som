/*
 * Created on 2009/07/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepDepositDetailDaoクラス
 * @author kanri-user
 */
public interface RepDepositDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "claimNo";

	/**
	 * RepDepositDetailメソッド
	 * 
	 * @param claimno claimno
	 * @return RepDepositDetail
	 */
	List<RepDepositDetail> getListForReport(final ArrayList<String> claimno);
}
