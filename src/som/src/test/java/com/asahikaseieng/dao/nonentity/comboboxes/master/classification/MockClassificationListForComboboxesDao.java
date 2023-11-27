/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.classification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MockClassificationListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockClassificationListForComboboxesDao implements
		ClassificationListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockClassificationListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ClassificationListForComboboxes> getListForComboboxes(
			final BigDecimal dataType, final BigDecimal arDivision) {
		List<ClassificationListForComboboxes> list = new ArrayList<ClassificationListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* ClassificationListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ClassificationListForComboboxesを生成する
	 * @param i インデックス
	 * @return ClassificationListForComboboxes
	 */
	private ClassificationListForComboboxes createBean(final int i) {
		ClassificationListForComboboxes bean = new ClassificationListForComboboxes();
		bean.setCategoryDivision("1");
		bean.setCategoryName("NAME" + i);
		return bean;
	}
}
