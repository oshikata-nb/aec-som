/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlist;

import java.util.List;

/**
 * VenderListDaoクラス
 * @author kanri-user
 */
public interface VenderListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.venderlist.VenderList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<VenderList>
	 */
	List<VenderList> getList(final VenderListPagerCondition condition);

	/** ARGSアノテーション getVenderList */
	String getVenderList_ARGS = "";

	/**
	 * List検索メソッド
	 * @return List
	 */
	List<VenderList> getVenderList();

	/** ARGSアノテーション getSearchNameList */
	String getSearchNameList_ARGS = "venderName";

	/**
	 * List検索メソッド
	 * @param venderName 取引先名称
	 * @return List
	 */
	List<VenderList> getSearchNameList(final String venderName);

	/** ARGSアノテーション checkVenderCd */
	String checkVenderCd_ARGS = "venderDivision,venderCd";

	/**
	 * VenderCdチェックメソッド
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先名称
	 * @return List
	 */
	VenderList checkVenderCd(final String venderDivision, final String venderCd);
}
