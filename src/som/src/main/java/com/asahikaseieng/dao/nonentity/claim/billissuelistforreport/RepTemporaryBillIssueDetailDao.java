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
 * RepBillIssueDetailDaoクラス
 * @author kanri-user
 */
public interface RepTemporaryBillIssueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.RepBillIssueDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "claimNo";

	/**
	 * RepBillIssueDetailメソッド
	 *
	 * @param claimno claimno
	 * @return RepBillIssueDetail
	 */
	List<RepBillIssueDetail> getListForReport(final ArrayList<String> claimno);
}
