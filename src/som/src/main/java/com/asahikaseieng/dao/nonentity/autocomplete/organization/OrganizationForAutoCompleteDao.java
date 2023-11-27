/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.organization;

import java.util.List;

/**
 * OrganizationForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface OrganizationForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.organization.OrganizationForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,rowlimit";

	/**
	 * OrganizationForAutoCompleteメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @param rowlimit 行上限
	 * @return List<OrganizationForAutoComplete>
	 */
	List<OrganizationForAutoComplete> getSearchList(
			final String organizationCd, final String rowlimit);
}
