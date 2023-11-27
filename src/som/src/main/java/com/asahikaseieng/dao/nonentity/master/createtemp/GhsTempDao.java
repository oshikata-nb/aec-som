/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

import java.util.List;

/**
 * GHSTemp（原処方情報からの取得）Daoインターフェース.
 * @author hongyo
 */
public interface GhsTempDao {

	/** BEANアノテーション. */
	Class BEAN = GhsTemp.class;


	/** QUERYアノテーション. getList()*/
	String getList_QUERY = "ORDER BY SEQ";
	/**
	 * リスト取得.
	 * @return GhsTemp
	 */
	List<GhsTemp> getList();

}
