/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgrouplist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OperationGroupConditionクラス.
 * @author jbd
 */
public class OperationGroupListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OperationGroupListPagerCondition() {
	}

	private String operationGroupCd;

	private String operationGroupName;

	/**
	 * operationGroupNameを取得します。
	 * @return operationGroupName
	 */
	public String getOperationGroupName() {
		return operationGroupName;
	}

	/**
	 * operationGroupNameを設定します。
	 * @param operationGroupName operationGroupName
	 */
	public void setOperationGroupName(final String operationGroupName) {
		this.operationGroupName = AecTextUtils.likeFilter(operationGroupName);
	}

	/**
	 * operationGroupCdを取得します。
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * operationGroupCdを設定します。
	 * @param operationGroupCd operationGroupCd
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = AecTextUtils.likeFilter(operationGroupCd);
	}
}
