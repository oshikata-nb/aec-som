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
import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsedDao;

/**
 * 製品別原材料消費量リスト ロジック実装クラス
 * @author t2712372
 */
public class DirectionMaterialUsedLogicImpl implements
		DirectionMaterialUsedLogic {

	private RepDirectionMaterialUsedDao repDirectionMaterialUsedDao;

	/**
	 * コンストラクタ.
	 */
	public DirectionMaterialUsedLogicImpl() {
	}

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 */
	public List<RepDirectionMaterialUsed> getList(
			final RepDirectionMaterialUsedCondition condition) {

		List<RepDirectionMaterialUsed> list = repDirectionMaterialUsedDao
				.getListForReport(condition);

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * DirectionMaterialUsedを設定します。
	 * @param repDirectionMaterialUsedDao repDirectionMaterialUsedDao
	 */
	public void setRepDirectionMaterialUsedDao(
			final RepDirectionMaterialUsedDao repDirectionMaterialUsedDao) {
		this.repDirectionMaterialUsedDao = repDirectionMaterialUsedDao;
	}

}
