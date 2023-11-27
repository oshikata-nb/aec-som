/*
 * Created on 2009/07/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissuelistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepBillIssueHeaderDaoクラス
 * @author kanri-user
 */
public interface RepBillIssueHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "claimNo";

	/**
	 * RepBillIssueHeaderメソッド
	 * 
	 * @param claimno claimno
	 * @return RepBillIssueHeader
	 */
	List<RepBillIssueHeader> getListForReport(final ArrayList<String> claimno);
}
