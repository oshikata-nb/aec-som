/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 製造指図新規入力-自社／花王区分
 * @author tosco
 */
public class SelectDirectionShipperDivision extends AbstractSelectComboBox {
	/** 自社／花王区分－自社 */
	public static final String OWN = "1";
	/** 自社／花王区分－花王 */
	public static final String KAO = "2";

	/** 値 */
	private static final String[] VALUE =
		{OWN, KAO};
	/** ラベル */
	private static final String[] LABEL =
		{"自社", "花王"};

	/**
	 * コンストラクタ
	 */
	public SelectDirectionShipperDivision() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectDirectionShipperDivision(final boolean zero, final boolean withNum) {
		super(zero, withNum);
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
