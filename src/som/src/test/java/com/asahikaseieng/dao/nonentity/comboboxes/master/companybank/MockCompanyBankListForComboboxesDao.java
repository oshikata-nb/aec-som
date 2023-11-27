/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.companybank;

import java.util.ArrayList;
import java.util.List;

/**
 * MockCompanyBankListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockCompanyBankListForComboboxesDao implements
		CompanyBankListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockCompanyBankListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CompanyBankListForComboboxes> getListForComboboxes() {
		List<CompanyBankListForComboboxes> list = new ArrayList<CompanyBankListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* CompanyBankListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CompanyBankListForComboboxesを生成する
	 * @param i インデックス
	 * @return CompanyBankListForComboboxes
	 */
	private CompanyBankListForComboboxes createBean(final int i) {
		CompanyBankListForComboboxes bean = new CompanyBankListForComboboxes();
		bean.setBankMasterCd("CD" + i);
		bean.setBankMasterName("NAME" + i);
		return bean;
	}
}
