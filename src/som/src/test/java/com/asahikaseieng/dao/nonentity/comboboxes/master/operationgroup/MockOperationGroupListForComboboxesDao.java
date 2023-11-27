/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup;

import java.util.ArrayList;
import java.util.List;

/**
 * MockOperationGroupListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockOperationGroupListForComboboxesDao implements
		OperationGroupListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationGroupListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationGroupListForComboboxes> getListForComboboxes() {
		List<OperationGroupListForComboboxes> list = new ArrayList<OperationGroupListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* OperationGroupListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationGroupListForComboboxesを生成する
	 * @param i インデックス
	 * @return OperationGroupListForComboboxes
	 */
	private OperationGroupListForComboboxes createBean(final int i) {
		OperationGroupListForComboboxes bean = new OperationGroupListForComboboxes();
		bean.setOperationGroupCd("OPERATION_GROUP_CD" + i);
		bean.setOperationGroupName("NAME" + i);
		return bean;
	}
}
