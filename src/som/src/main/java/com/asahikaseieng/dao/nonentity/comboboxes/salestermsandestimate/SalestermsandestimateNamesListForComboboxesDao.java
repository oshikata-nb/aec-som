
/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate;

import java.util.List;

/**
 * NamesListDaoクラス
 * @author t0011036
 */
public interface SalestermsandestimateNamesListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes.class;

	/** ARGSアノテーション getProcessDivision */
	String getProcessDivision_ARGS = "nameDivision";

	/**
	 * Listメソッド
	 * 
	 * @param nameDivision nameDivision
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	List<SalestermsandestimateNamesListForComboboxes> getProcessDivision(final String nameDivision);
	
	/** ARGSアノテーション getStatus */
	String getStatus_ARGS = "nameDivision";

	/**
	 * Listメソッド
	 * 
	 * @param nameDivision nameDivision
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	List<SalestermsandestimateNamesListForComboboxes> getStatus(final String nameDivision);
}
