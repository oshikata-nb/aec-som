/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repMaterialDirectionDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepMaterialDirectionDetailDaoクラス
 * @author kanri-user
 */
public interface RepMaterialDirectionDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repMaterialDirectionDetail.RepMaterialDirectionDetail.class;

	/** ARGSアノテーション getMaterialDirectionDetailList */
	String getMaterialDirectionDetailList_ARGS = "directionNo";

	/**
	 * Listメソッド
	 * 
	 * @param directionno directionno
	 * @return List
	 */
	List<RepMaterialDirectionDetail> getMaterialDirectionDetailList(
			final ArrayList<String> directionno);
}
