/*
 * Created on Mon Jan 19 16:58:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.classification;

/**
 * ClassificationDaoインターフェース.
 * @author t0011036
 */
public interface ClassificationDao {

	/** BEANアノテーション. */
	Class BEAN = Classification.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Classification
	 * @return Insert件数
	 */
	int insert(Classification bean);

	/**
	 * Update.
	 * @param bean Classification
	 * @return Update件数
	 */
	int update(Classification bean);

	/**
	 * Delete.
	 * @param bean Classification
	 * @return Delete件数
	 */
	int delete(Classification bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CATEGORY_DIVISION,DATA_TOTAL_DIVISION,DATA_TYPE";

	/**
	 * エンティティ取得.
	 * @param categoryDivision categoryDivision
	 * @param dataTotalDivision dataTotalDivision
	 * @param dataType dataType
	 * @return Classification
	 */
	Classification getEntity(final String categoryDivision,
			final java.math.BigDecimal dataTotalDivision,
			final java.math.BigDecimal dataType);
}
