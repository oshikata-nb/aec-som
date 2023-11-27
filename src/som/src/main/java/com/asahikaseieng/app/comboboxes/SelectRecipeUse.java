/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本処方-用途
 * @author tosco
 */
public class SelectRecipeUse extends AbstractSelectComboBox {
	/** 用途-Planning */
	public static final String RECIPE_USE_PLANNING = "1";
	/** 用途-Costing */
	public static final String RECIPE_USE_COSTING = "2";
	/** 用途-製造 */
	public static final String RECIPE_USE_PRODUCTION = "3";
	/** 用途-包装 */
	public static final String RECIPE_USE_PACKING = "4";

	/** 値 */
	private static final String[] VALUE =
		{RECIPE_USE_PLANNING, RECIPE_USE_COSTING, RECIPE_USE_PRODUCTION, RECIPE_USE_PACKING};
	/** ラベル */
	private static final String[] LABEL = {"Planning", "Costing", "製造", "包装"};

	/** 製造以降のみを表示するフラグ */
	private boolean half;
	/**
	 * コンストラクタ
	 */
	public SelectRecipeUse() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 * @param half boolean
	 */
	public SelectRecipeUse(final boolean zero, final boolean withNum, final boolean half) {
		super(zero, withNum);
		this.half = half;
		setItem();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getLabels()
	 * @return Label String.
	 */
	@Override
	public String[] getLabels() {

		return super.getLab();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getValues()
	 * @return Values String.
	 */
	@Override
	public String[] getValues() {
		return super.getVal();
	}

	@Override
	void setItem() {
		List<ComboBoxItems> cb = new ArrayList<ComboBoxItems>();

		if (LABEL.length == VALUE.length) {
			for (int i = 0; i < VALUE.length; i++) {
				if (half && i < 2) {
					continue;
				}
				ComboBoxItems cbItems = new ComboBoxItems();
				cbItems.setLabales(LABEL[i]);
				cbItems.setValues(VALUE[i]);
				cb.add(cbItems);
			}
		}
		super.setComboBoxList(cb);
	}
	/**
	 * コードに対応する名称を返す。
	 * 対応するコードがない場合はnullを返す
	 * @param code コード
	 * @return 名称
	 */
	public static final String getName(final String code) {
		String res = null;
		int len = VALUE.length;
		for (int i = 0; i < len; i++) {
			if (VALUE[i].equals(code)) {
				res = LABEL[i];
				break;
			}
		}
		return res;
	}
}
