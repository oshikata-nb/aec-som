/*
 * Created on 2009/09/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classificationdetail;

import java.math.BigDecimal;

/**
 * ClassificationDetailDaoクラス
 * @author t0011036
 */
public interface ClassificationDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "dataType, categoryDivision, dataTotalDivision";

	/**
	 * ClassificationDetailメソッド
	 * 
	 * @param dataType dataType
	 * @param categoryDivision categoryDivision
	 * @param dataTotalDivision dataTotalDivision
	 * @return ClassificationDetail
	 */
	ClassificationDetail getEntity(final BigDecimal dataType,
			final String categoryDivision, final BigDecimal dataTotalDivision);
}
