/*
 * Created on 2009/06/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repBuyingOrderHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepBuyingOrderHeaderDaoクラス
 * @author kanri-user
 */
public interface RepBuyingOrderHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repBuyingOrderHeader.RepBuyingOrderHeader.class;

	/** ARGSアノテーション getBuyingOrderHeaderList */
	String getBuyingOrderHeaderList_ARGS = "buyingNo";

	/**
	 * Listメソッド
	 * 
	 * @param buyingNo buyingNo
	 * @return List
	 */
	List<RepBuyingOrderHeader> getBuyingOrderHeaderList(
			final ArrayList<String> buyingNo);
}
