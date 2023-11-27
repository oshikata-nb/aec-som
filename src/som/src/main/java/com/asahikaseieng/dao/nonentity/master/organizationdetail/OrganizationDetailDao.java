/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationdetail;

/**
 * OrganizationDetailDaoクラス
 * @author t0011036
 */
public interface OrganizationDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "organizationCd";

	/**
	 * OrganizationDetailメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @return OrganizationDetail
	 */
	OrganizationDetail getEntity(final String organizationCd);
}
