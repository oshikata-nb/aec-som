/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster;

import java.util.List;

/**
 * TaxMasteryListForComboboxesDaoクラス
 * @author t0011036
 */
public interface TaxMasterListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getSalesTaxListForComboboxes_ARGS = "";

	/**
	 * TaxMasterListForComboboxesメソッド
	 * 
	 * @return List<TaxMasterListForComboboxes>
	 */
	List<TaxMasterListForComboboxes> getSalesTaxListForComboboxes();
	
	/** ARGSアノテーション getListForComboboxes */
	String getPurchaseTaxListForComboboxes_ARGS = "";

	/**
	 * TaxMasterListForComboboxesメソッド
	 * 
	 * @return List<TaxMasterListForComboboxes>
	 */
	List<TaxMasterListForComboboxes> getPurchaseTaxListForComboboxes();
}
