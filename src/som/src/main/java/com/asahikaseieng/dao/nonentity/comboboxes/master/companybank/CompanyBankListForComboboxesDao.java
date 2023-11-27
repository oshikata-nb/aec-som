/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.companybank;

import java.util.List;

/**
 * CompanyBankListForComboboxesDaoクラス
 * @author t0011036
 */
public interface CompanyBankListForComboboxesDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes.class;

	/** ARGSアノテーション getListForComboboxes */
	String getListForComboboxes_ARGS = "";

	/**
	 * CompanyBankListForComboboxesメソッド
	 * 
	 * @return List<CompanyBankListForComboboxes>
	 */
	List<CompanyBankListForComboboxes> getListForComboboxes();
}
