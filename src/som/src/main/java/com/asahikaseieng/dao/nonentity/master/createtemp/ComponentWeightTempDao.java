/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

import java.util.List;

/**
 * 成分重量Temp（原処方情報からの取得）Daoインターフェース.
 * @author hongyo
 */
public interface ComponentWeightTempDao {

	/** BEANアノテーション. */
	Class BEAN = ComponentWeightTemp.class;

	/** QUERYアノテーション. getList()*/
	String getList_QUERY = "ORDER BY SEQ";
	/**
	 * リスト取得.
	 * @return ComponentWeightTemp
	 */
	List<ComponentWeightTemp> getList();

}
