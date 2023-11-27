/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companydetail;

/**
 * CompanyDetailDaoクラス
 * @author t0011036
 */
public interface CompanyDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "companyCd";

	/**
	 * CompanyDetailメソッド
	 * 
	 * @param companyCd companyCd
	 * @return CompanyDetail
	 */
	CompanyDetail getEntity(final String companyCd);
}
