/*
 * Created on 2009/10/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmaterial;

import java.util.List;

/**
 * InoutMaterialDaoクラス
 * @author kanri-user
 */
public interface InoutMaterialDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutMaterial>
	 */
	List<InoutMaterial> getList(InoutMaterialCondition condition);
}
