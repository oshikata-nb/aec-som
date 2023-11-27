/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgroupdelete;

/**
 * OffsetGroupDeleteDaoクラス
 * @author t0011036
 */
public interface OffsetGroupDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.offsetgroupdelete.OffsetGroupDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "offsetGroupCd";

	/**
	 * OffsetGroupDeleteメソッド
	 * 
	 * @param offsetGroupCd offsetGroupCd
	 * @return int 削除件数
	 */
	int delete(final String offsetGroupCd);
}
