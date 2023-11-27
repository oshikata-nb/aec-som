/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderclassdetail;

import java.math.BigDecimal;

/**
 * VenderClassDetailDaoクラス
 * @author t0011036
 */
public interface VenderClassDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.venderclassdetail.VenderClassDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "dataType, categoryDivision";

	/**
	 * VenderClassDetailメソッド
	 * 
	 * @param dataType dataType
	 * @param categoryDivision categoryDivision
	 * @return VenderClassDetail
	 */
	VenderClassDetail getEntity(final BigDecimal dataType,
			final String categoryDivision);
}
