/*
 * Created on 2009/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionmaterialusedforreport;

import java.util.List;

/**
 * RepDirectionMaterialUsedDaoクラス
 * @author kanri-user
 */
public interface RepDirectionMaterialUsedDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsed.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * RepDirectionMaterialUsedメソッド
	 * 
	 * @param condition condition
	 * @return RepDirectionMaterialUsed
	 */
	List<RepDirectionMaterialUsed> getListForReport(
			RepDirectionMaterialUsedCondition condition);
}
