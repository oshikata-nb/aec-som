/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * MgrecipeResouceDaoインターフェイス
 * @author tosco
 */
public interface MgrecipeResouceDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeResouceDetail> BEAN = MgrecipeResouceDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "RESOUCE_CD";
	/**
	 * 検索メソッド
	 * @param resouceCd resouceCd
	 * @return MgrecipeResouceDetail
	 */
	MgrecipeResouceDetail getEntity(String resouceCd);
}
