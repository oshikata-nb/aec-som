/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.direction;

import java.util.List;

import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsed;
import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsedCondition;

/**
 * 製品別原材料消費量リスト ロジッククラス interface.
 * @author t2712372
 */
public interface DirectionMaterialUsedLogic {

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 */
	List<RepDirectionMaterialUsed> getList(
			final RepDirectionMaterialUsedCondition condition);

}
