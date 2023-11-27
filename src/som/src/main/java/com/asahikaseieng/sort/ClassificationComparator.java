/*
 * Created on Mon Apr 09 09:43:51 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.sort;

import java.math.BigDecimal;
import java.util.Comparator;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ソート実装クラス
 * @author t0011036
 */
public class ClassificationComparator implements Comparator {
	private static final long serialVersionUID = 1L;

	/** 昇順 */
	public static final int ASC = 1;

	/** 降順 */
	public static final int DESC = -1;

	private int sort = ASC;

	/**
	 * コンストラクタ.
	 */
	public ClassificationComparator() {
	}

	/**
	 * 
	 * コンストラクタ
	 * @param sort ASC=昇順 / DESC=降順
	 */
	public ClassificationComparator(final int sort) {
		this.sort = sort;
	}

	/**
	 * 
	 * ソート
	 * @param arg0 比較元
	 * @param arg1 比較先
	 * @return 0:等しい 1:より大きい -1:より小さい
	 */
	public int compare(final Object arg0, final Object arg1) {
		ClassificationListForComboboxes bean1 = (ClassificationListForComboboxes) arg0;
		ClassificationListForComboboxes bean2 = (ClassificationListForComboboxes) arg1;

		if (bean1 == null || bean2 == null) {
			return 0;
		}

		BigDecimal num1 = AecNumberUtils.convertBigDecimal(bean1
				.getCategoryDivision());
		BigDecimal num2 = AecNumberUtils.convertBigDecimal(bean2
				.getCategoryDivision());
		return num1.compareTo(num2) * sort;
	}
}
