/*
 * Created on 2009/06/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repLabelYobiyokai;

import java.util.ArrayList;
import java.util.List;

/**
 * RepLabelYobiyokaiDaoクラス
 * @author kanri-user
 */
public interface RepLabelYobiyokaiDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repLabelYobiyokai.RepLabelYobiyokai.class;

	/** ARGSアノテーション getYobiyokaiList */
	String getYobiyokaiList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepLabelYobiyokai> getYobiyokaiList(final ArrayList<String> directionno);
}
