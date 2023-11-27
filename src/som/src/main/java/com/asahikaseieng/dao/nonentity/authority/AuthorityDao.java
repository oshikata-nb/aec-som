/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.authority;

import java.util.List;

/**
 * AuthorityDaoクラス
 * @author jbd
 */
public interface AuthorityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.authority.Authority.class;

	/** ARGSアノテーション getGadgetAuthorityList */
	String getGadgetAuthorityList_ARGS = "tantoRoleIds, belongRoleIds";

	/**
	 * ガジェット権限List取得メソッド
	 * 
	 * @param tantoRoleIds String[]
	 * @param belongRoleIds String[]
	 * @return List
	 */
	List<Authority> getGadgetAuthorityList(final String[] tantoRoleIds,
			final String[] belongRoleIds);
}
