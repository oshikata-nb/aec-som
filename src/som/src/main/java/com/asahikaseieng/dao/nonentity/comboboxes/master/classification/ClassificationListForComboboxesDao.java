/*
 * Created on 2009/09/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.classification;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassificationListForComboboxesDaoクラス
 * @author t0011036
 */
public interface ClassificationListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "dataType, arDivision";

	/**
	 * ClassificationListForComboboxesメソッド
	 * 
	 * @param dataType dataType
	 * @param arDivision arDivision
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getListForComboboxes(
			final BigDecimal dataType, final BigDecimal arDivision);
}
