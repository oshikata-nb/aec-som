/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial;
import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterialCondition;

/**
 * 原材料別入出庫一覧表 ロジッククラス interface.
 * @author t1344224
 */
public interface InoutMaterialLogic {
	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 */
	List<InoutMaterial> getList(final InoutMaterialCondition condition);

}
