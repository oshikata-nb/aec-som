/*
 * Created on 2009/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateupdate;

import com.asahikaseieng.dao.entity.estimate.Estimate;

/**
 * EstimateUpdateDaoクラス
 * @author t0011036
 */
public interface EstimateUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.estimateupdate.EstimateUpdate.class;

	/** ARGSアノテーション update */
	String update_ARGS = "bean";

	/**
	 * EstimateUpdateメソッド
	 * 
	 * @param bean 更新データ
	 * @return int 更新件数
	 */
	int update(final Estimate bean);
}
