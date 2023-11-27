/*
 * Created on 2009/06/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.comboboxes;

import java.util.ArrayList;
import java.util.List;

/**
 * 支払入力－手形種別用コンボボックスクラス
 * @author tosco
 */
public class SelectPaymentNoteDivision extends AbstractSelectComboBox {

	private final String[] myValue = {"1", "2"};

	private final String[] myLabel = {"約束手形", "為替手形"};

	/**
	 * コンストラクタ
	 */
	public SelectPaymentNoteDivision() {
		setItem();
	}

	/**
	 * コンストラクタ
	 * @param zero boolean
	 * @param withNum boolean
	 */
	public SelectPaymentNoteDivision(final boolean zero, final boolean withNum) {
		super(zero, withNum);
		setItem();
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getLabels()
	 * @return Label String.
	 */
	@Override
	public String[] getLabels() {

		String[] wLabels = super.getLab();
		String[] rLabels = new String[wLabels.length + 1];

		// 先頭にブランク行を設定する
		for (int i = 0; i < rLabels.length; i++) {
			if (i == 0) {
				rLabels[i] = "";
			} else {
				rLabels[i] = wLabels[i - 1];
			}
		}
		return rLabels;
	}

	/**
	 * @see com.asahikaseieng.app.comboboxes.AbstractSelectComboBox#getValues()
	 * @return Values String.
	 */
	@Override
	public String[] getValues() {
		String[] wVales = super.getVal();
		String[] rVales = new String[wVales.length + 1];

		// 先頭にブランク行の値を設定する
		for (int i = 0; i < rVales.length; i++) {
			if (i == 0) {
				rVales[i] = "";
			} else {
				rVales[i] = wVales[i - 1];
			}
		}
		return rVales;
	}

	@Override
	void setItem() {
		List<ComboBoxItems> cb = new ArrayList<ComboBoxItems>();

		if (myLabel.length == myValue.length) {

			for (int i = 0; i < myValue.length; i++) {
				ComboBoxItems cbItems = new ComboBoxItems();
				cbItems.setLabales(myLabel[i]);
				cbItems.setValues(myValue[i]);
				cb.add(cbItems);
			}
		}
		super.setComboBoxList(cb);
	}
}
