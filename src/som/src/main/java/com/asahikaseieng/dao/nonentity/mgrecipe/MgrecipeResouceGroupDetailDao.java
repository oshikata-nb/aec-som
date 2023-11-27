/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * MgrecipeResouceGroupDaoインターフェイス
 * @author tosco
 */
public interface MgrecipeResouceGroupDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeResouceGroupDetail> BEAN = MgrecipeResouceGroupDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "RESOUCE_GROUP_CD";
	/**
	 * 検索メソッド
	 *
	 * @param resouceGroupCd 設備グループコード
	 * @return MgrecipeResouceGroupDetail
	 */
	MgrecipeResouceGroupDetail getEntity(String resouceGroupCd);
}
