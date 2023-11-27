/*
 * Created on 2009/07/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepDepositHeaderDaoクラス
 * @author kanri-user
 */
public interface RepDepositHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.depositlistforreport.RepDepositHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "claimNo";

	/**
	 * RepDepositHeaderメソッド
	 * 
	 * @param claimno claimno
	 * @return RepDepositHeader
	 */
	List<RepDepositHeader> getListForReport(final ArrayList<String> claimno);
}
